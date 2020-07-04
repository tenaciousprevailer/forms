package com.tenacious.forms.web.rest;

import com.tenacious.forms.FormsApp;
import com.tenacious.forms.domain.UserResponse;
import com.tenacious.forms.repository.UserResponseRepository;
import com.tenacious.forms.service.UserResponseService;
import com.tenacious.forms.service.dto.UserResponseDTO;
import com.tenacious.forms.service.mapper.UserResponseMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link UserResponseResource} REST controller.
 */
@SpringBootTest(classes = FormsApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class UserResponseResourceIT {

    private static final Instant DEFAULT_DATE_CREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_CREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_TAKEN_BY = "AAAAAAAAAA";
    private static final String UPDATED_TAKEN_BY = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_DATA = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_DATA = "BBBBBBBBBB";

    @Autowired
    private UserResponseRepository userResponseRepository;

    @Autowired
    private UserResponseMapper userResponseMapper;

    @Autowired
    private UserResponseService userResponseService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserResponseMockMvc;

    private UserResponse userResponse;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserResponse createEntity(EntityManager em) {
        UserResponse userResponse = new UserResponse()
            .dateCreated(DEFAULT_DATE_CREATED)
            .takenBy(DEFAULT_TAKEN_BY)
            .responseData(DEFAULT_RESPONSE_DATA);
        return userResponse;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserResponse createUpdatedEntity(EntityManager em) {
        UserResponse userResponse = new UserResponse()
            .dateCreated(UPDATED_DATE_CREATED)
            .takenBy(UPDATED_TAKEN_BY)
            .responseData(UPDATED_RESPONSE_DATA);
        return userResponse;
    }

    @BeforeEach
    public void initTest() {
        userResponse = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserResponse() throws Exception {
        int databaseSizeBeforeCreate = userResponseRepository.findAll().size();
        // Create the UserResponse
        UserResponseDTO userResponseDTO = userResponseMapper.toDto(userResponse);
        restUserResponseMockMvc.perform(post("/api/user-responses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userResponseDTO)))
            .andExpect(status().isCreated());

        // Validate the UserResponse in the database
        List<UserResponse> userResponseList = userResponseRepository.findAll();
        assertThat(userResponseList).hasSize(databaseSizeBeforeCreate + 1);
        UserResponse testUserResponse = userResponseList.get(userResponseList.size() - 1);
        assertThat(testUserResponse.getDateCreated()).isEqualTo(DEFAULT_DATE_CREATED);
        assertThat(testUserResponse.getTakenBy()).isEqualTo(DEFAULT_TAKEN_BY);
        assertThat(testUserResponse.getResponseData()).isEqualTo(DEFAULT_RESPONSE_DATA);
    }

    @Test
    @Transactional
    public void createUserResponseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userResponseRepository.findAll().size();

        // Create the UserResponse with an existing ID
        userResponse.setId(1L);
        UserResponseDTO userResponseDTO = userResponseMapper.toDto(userResponse);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserResponseMockMvc.perform(post("/api/user-responses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userResponseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserResponse in the database
        List<UserResponse> userResponseList = userResponseRepository.findAll();
        assertThat(userResponseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUserResponses() throws Exception {
        // Initialize the database
        userResponseRepository.saveAndFlush(userResponse);

        // Get all the userResponseList
        restUserResponseMockMvc.perform(get("/api/user-responses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userResponse.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateCreated").value(hasItem(DEFAULT_DATE_CREATED.toString())))
            .andExpect(jsonPath("$.[*].takenBy").value(hasItem(DEFAULT_TAKEN_BY)))
            .andExpect(jsonPath("$.[*].responseData").value(hasItem(DEFAULT_RESPONSE_DATA)));
    }
    
    @Test
    @Transactional
    public void getUserResponse() throws Exception {
        // Initialize the database
        userResponseRepository.saveAndFlush(userResponse);

        // Get the userResponse
        restUserResponseMockMvc.perform(get("/api/user-responses/{id}", userResponse.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userResponse.getId().intValue()))
            .andExpect(jsonPath("$.dateCreated").value(DEFAULT_DATE_CREATED.toString()))
            .andExpect(jsonPath("$.takenBy").value(DEFAULT_TAKEN_BY))
            .andExpect(jsonPath("$.responseData").value(DEFAULT_RESPONSE_DATA));
    }
    @Test
    @Transactional
    public void getNonExistingUserResponse() throws Exception {
        // Get the userResponse
        restUserResponseMockMvc.perform(get("/api/user-responses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserResponse() throws Exception {
        // Initialize the database
        userResponseRepository.saveAndFlush(userResponse);

        int databaseSizeBeforeUpdate = userResponseRepository.findAll().size();

        // Update the userResponse
        UserResponse updatedUserResponse = userResponseRepository.findById(userResponse.getId()).get();
        // Disconnect from session so that the updates on updatedUserResponse are not directly saved in db
        em.detach(updatedUserResponse);
        updatedUserResponse
            .dateCreated(UPDATED_DATE_CREATED)
            .takenBy(UPDATED_TAKEN_BY)
            .responseData(UPDATED_RESPONSE_DATA);
        UserResponseDTO userResponseDTO = userResponseMapper.toDto(updatedUserResponse);

        restUserResponseMockMvc.perform(put("/api/user-responses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userResponseDTO)))
            .andExpect(status().isOk());

        // Validate the UserResponse in the database
        List<UserResponse> userResponseList = userResponseRepository.findAll();
        assertThat(userResponseList).hasSize(databaseSizeBeforeUpdate);
        UserResponse testUserResponse = userResponseList.get(userResponseList.size() - 1);
        assertThat(testUserResponse.getDateCreated()).isEqualTo(UPDATED_DATE_CREATED);
        assertThat(testUserResponse.getTakenBy()).isEqualTo(UPDATED_TAKEN_BY);
        assertThat(testUserResponse.getResponseData()).isEqualTo(UPDATED_RESPONSE_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingUserResponse() throws Exception {
        int databaseSizeBeforeUpdate = userResponseRepository.findAll().size();

        // Create the UserResponse
        UserResponseDTO userResponseDTO = userResponseMapper.toDto(userResponse);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserResponseMockMvc.perform(put("/api/user-responses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userResponseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserResponse in the database
        List<UserResponse> userResponseList = userResponseRepository.findAll();
        assertThat(userResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserResponse() throws Exception {
        // Initialize the database
        userResponseRepository.saveAndFlush(userResponse);

        int databaseSizeBeforeDelete = userResponseRepository.findAll().size();

        // Delete the userResponse
        restUserResponseMockMvc.perform(delete("/api/user-responses/{id}", userResponse.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserResponse> userResponseList = userResponseRepository.findAll();
        assertThat(userResponseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
