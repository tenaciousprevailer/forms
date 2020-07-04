package com.tenacious.forms.web.rest;

import com.tenacious.forms.FormsApp;
import com.tenacious.forms.domain.SurveyConfiguration;
import com.tenacious.forms.repository.SurveyConfigurationRepository;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.tenacious.forms.domain.enumeration.VisibilityType;
import com.tenacious.forms.domain.enumeration.VisibilityType;
/**
 * Integration tests for the {@link SurveyConfigurationResource} REST controller.
 */
@SpringBootTest(classes = FormsApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SurveyConfigurationResourceIT {

    private static final VisibilityType DEFAULT_SURVEY_VISIBILITY = VisibilityType.ANYONE_WITH_LINK;
    private static final VisibilityType UPDATED_SURVEY_VISIBILITY = VisibilityType.SPECIFIC_USERS_AND_GROUPS;

    private static final String DEFAULT_USERS_HAVING_VISIBILITY = "AAAAAAAAAA";
    private static final String UPDATED_USERS_HAVING_VISIBILITY = "BBBBBBBBBB";

    private static final String DEFAULT_GROUPS_HAVING_VISIBILITY = "AAAAAAAAAA";
    private static final String UPDATED_GROUPS_HAVING_VISIBILITY = "BBBBBBBBBB";

    private static final VisibilityType DEFAULT_RESULT_VISIBILITY = VisibilityType.ANYONE_WITH_LINK;
    private static final VisibilityType UPDATED_RESULT_VISIBILITY = VisibilityType.SPECIFIC_USERS_AND_GROUPS;

    private static final String DEFAULT_USERS_HAVING_RESULT_VISIBILITY = "AAAAAAAAAA";
    private static final String UPDATED_USERS_HAVING_RESULT_VISIBILITY = "BBBBBBBBBB";

    private static final String DEFAULT_GROUPS_HAVING_RESULT_VISIBILITY = "AAAAAAAAAA";
    private static final String UPDATED_GROUPS_HAVING_RESULT_VISIBILITY = "BBBBBBBBBB";

    private static final String DEFAULT_MAINTAINER_USERS = "AAAAAAAAAA";
    private static final String UPDATED_MAINTAINER_USERS = "BBBBBBBBBB";

    private static final String DEFAULT_MAINTAINER_USER_GROUPS = "AAAAAAAAAA";
    private static final String UPDATED_MAINTAINER_USER_GROUPS = "BBBBBBBBBB";

    @Autowired
    private SurveyConfigurationRepository surveyConfigurationRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSurveyConfigurationMockMvc;

    private SurveyConfiguration surveyConfiguration;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SurveyConfiguration createEntity(EntityManager em) {
        SurveyConfiguration surveyConfiguration = new SurveyConfiguration()
            .surveyVisibility(DEFAULT_SURVEY_VISIBILITY)
            .usersHavingVisibility(DEFAULT_USERS_HAVING_VISIBILITY)
            .groupsHavingVisibility(DEFAULT_GROUPS_HAVING_VISIBILITY)
            .resultVisibility(DEFAULT_RESULT_VISIBILITY)
            .usersHavingResultVisibility(DEFAULT_USERS_HAVING_RESULT_VISIBILITY)
            .groupsHavingResultVisibility(DEFAULT_GROUPS_HAVING_RESULT_VISIBILITY)
            .maintainerUsers(DEFAULT_MAINTAINER_USERS)
            .maintainerUserGroups(DEFAULT_MAINTAINER_USER_GROUPS);
        return surveyConfiguration;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SurveyConfiguration createUpdatedEntity(EntityManager em) {
        SurveyConfiguration surveyConfiguration = new SurveyConfiguration()
            .surveyVisibility(UPDATED_SURVEY_VISIBILITY)
            .usersHavingVisibility(UPDATED_USERS_HAVING_VISIBILITY)
            .groupsHavingVisibility(UPDATED_GROUPS_HAVING_VISIBILITY)
            .resultVisibility(UPDATED_RESULT_VISIBILITY)
            .usersHavingResultVisibility(UPDATED_USERS_HAVING_RESULT_VISIBILITY)
            .groupsHavingResultVisibility(UPDATED_GROUPS_HAVING_RESULT_VISIBILITY)
            .maintainerUsers(UPDATED_MAINTAINER_USERS)
            .maintainerUserGroups(UPDATED_MAINTAINER_USER_GROUPS);
        return surveyConfiguration;
    }

    @BeforeEach
    public void initTest() {
        surveyConfiguration = createEntity(em);
    }

    @Test
    @Transactional
    public void createSurveyConfiguration() throws Exception {
        int databaseSizeBeforeCreate = surveyConfigurationRepository.findAll().size();
        // Create the SurveyConfiguration
        restSurveyConfigurationMockMvc.perform(post("/api/survey-configurations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(surveyConfiguration)))
            .andExpect(status().isCreated());

        // Validate the SurveyConfiguration in the database
        List<SurveyConfiguration> surveyConfigurationList = surveyConfigurationRepository.findAll();
        assertThat(surveyConfigurationList).hasSize(databaseSizeBeforeCreate + 1);
        SurveyConfiguration testSurveyConfiguration = surveyConfigurationList.get(surveyConfigurationList.size() - 1);
        assertThat(testSurveyConfiguration.getSurveyVisibility()).isEqualTo(DEFAULT_SURVEY_VISIBILITY);
        assertThat(testSurveyConfiguration.getUsersHavingVisibility()).isEqualTo(DEFAULT_USERS_HAVING_VISIBILITY);
        assertThat(testSurveyConfiguration.getGroupsHavingVisibility()).isEqualTo(DEFAULT_GROUPS_HAVING_VISIBILITY);
        assertThat(testSurveyConfiguration.getResultVisibility()).isEqualTo(DEFAULT_RESULT_VISIBILITY);
        assertThat(testSurveyConfiguration.getUsersHavingResultVisibility()).isEqualTo(DEFAULT_USERS_HAVING_RESULT_VISIBILITY);
        assertThat(testSurveyConfiguration.getGroupsHavingResultVisibility()).isEqualTo(DEFAULT_GROUPS_HAVING_RESULT_VISIBILITY);
        assertThat(testSurveyConfiguration.getMaintainerUsers()).isEqualTo(DEFAULT_MAINTAINER_USERS);
        assertThat(testSurveyConfiguration.getMaintainerUserGroups()).isEqualTo(DEFAULT_MAINTAINER_USER_GROUPS);
    }

    @Test
    @Transactional
    public void createSurveyConfigurationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = surveyConfigurationRepository.findAll().size();

        // Create the SurveyConfiguration with an existing ID
        surveyConfiguration.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSurveyConfigurationMockMvc.perform(post("/api/survey-configurations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(surveyConfiguration)))
            .andExpect(status().isBadRequest());

        // Validate the SurveyConfiguration in the database
        List<SurveyConfiguration> surveyConfigurationList = surveyConfigurationRepository.findAll();
        assertThat(surveyConfigurationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSurveyConfigurations() throws Exception {
        // Initialize the database
        surveyConfigurationRepository.saveAndFlush(surveyConfiguration);

        // Get all the surveyConfigurationList
        restSurveyConfigurationMockMvc.perform(get("/api/survey-configurations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(surveyConfiguration.getId().intValue())))
            .andExpect(jsonPath("$.[*].surveyVisibility").value(hasItem(DEFAULT_SURVEY_VISIBILITY.toString())))
            .andExpect(jsonPath("$.[*].usersHavingVisibility").value(hasItem(DEFAULT_USERS_HAVING_VISIBILITY)))
            .andExpect(jsonPath("$.[*].groupsHavingVisibility").value(hasItem(DEFAULT_GROUPS_HAVING_VISIBILITY)))
            .andExpect(jsonPath("$.[*].resultVisibility").value(hasItem(DEFAULT_RESULT_VISIBILITY.toString())))
            .andExpect(jsonPath("$.[*].usersHavingResultVisibility").value(hasItem(DEFAULT_USERS_HAVING_RESULT_VISIBILITY)))
            .andExpect(jsonPath("$.[*].groupsHavingResultVisibility").value(hasItem(DEFAULT_GROUPS_HAVING_RESULT_VISIBILITY)))
            .andExpect(jsonPath("$.[*].maintainerUsers").value(hasItem(DEFAULT_MAINTAINER_USERS)))
            .andExpect(jsonPath("$.[*].maintainerUserGroups").value(hasItem(DEFAULT_MAINTAINER_USER_GROUPS)));
    }
    
    @Test
    @Transactional
    public void getSurveyConfiguration() throws Exception {
        // Initialize the database
        surveyConfigurationRepository.saveAndFlush(surveyConfiguration);

        // Get the surveyConfiguration
        restSurveyConfigurationMockMvc.perform(get("/api/survey-configurations/{id}", surveyConfiguration.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(surveyConfiguration.getId().intValue()))
            .andExpect(jsonPath("$.surveyVisibility").value(DEFAULT_SURVEY_VISIBILITY.toString()))
            .andExpect(jsonPath("$.usersHavingVisibility").value(DEFAULT_USERS_HAVING_VISIBILITY))
            .andExpect(jsonPath("$.groupsHavingVisibility").value(DEFAULT_GROUPS_HAVING_VISIBILITY))
            .andExpect(jsonPath("$.resultVisibility").value(DEFAULT_RESULT_VISIBILITY.toString()))
            .andExpect(jsonPath("$.usersHavingResultVisibility").value(DEFAULT_USERS_HAVING_RESULT_VISIBILITY))
            .andExpect(jsonPath("$.groupsHavingResultVisibility").value(DEFAULT_GROUPS_HAVING_RESULT_VISIBILITY))
            .andExpect(jsonPath("$.maintainerUsers").value(DEFAULT_MAINTAINER_USERS))
            .andExpect(jsonPath("$.maintainerUserGroups").value(DEFAULT_MAINTAINER_USER_GROUPS));
    }
    @Test
    @Transactional
    public void getNonExistingSurveyConfiguration() throws Exception {
        // Get the surveyConfiguration
        restSurveyConfigurationMockMvc.perform(get("/api/survey-configurations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSurveyConfiguration() throws Exception {
        // Initialize the database
        surveyConfigurationRepository.saveAndFlush(surveyConfiguration);

        int databaseSizeBeforeUpdate = surveyConfigurationRepository.findAll().size();

        // Update the surveyConfiguration
        SurveyConfiguration updatedSurveyConfiguration = surveyConfigurationRepository.findById(surveyConfiguration.getId()).get();
        // Disconnect from session so that the updates on updatedSurveyConfiguration are not directly saved in db
        em.detach(updatedSurveyConfiguration);
        updatedSurveyConfiguration
            .surveyVisibility(UPDATED_SURVEY_VISIBILITY)
            .usersHavingVisibility(UPDATED_USERS_HAVING_VISIBILITY)
            .groupsHavingVisibility(UPDATED_GROUPS_HAVING_VISIBILITY)
            .resultVisibility(UPDATED_RESULT_VISIBILITY)
            .usersHavingResultVisibility(UPDATED_USERS_HAVING_RESULT_VISIBILITY)
            .groupsHavingResultVisibility(UPDATED_GROUPS_HAVING_RESULT_VISIBILITY)
            .maintainerUsers(UPDATED_MAINTAINER_USERS)
            .maintainerUserGroups(UPDATED_MAINTAINER_USER_GROUPS);

        restSurveyConfigurationMockMvc.perform(put("/api/survey-configurations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSurveyConfiguration)))
            .andExpect(status().isOk());

        // Validate the SurveyConfiguration in the database
        List<SurveyConfiguration> surveyConfigurationList = surveyConfigurationRepository.findAll();
        assertThat(surveyConfigurationList).hasSize(databaseSizeBeforeUpdate);
        SurveyConfiguration testSurveyConfiguration = surveyConfigurationList.get(surveyConfigurationList.size() - 1);
        assertThat(testSurveyConfiguration.getSurveyVisibility()).isEqualTo(UPDATED_SURVEY_VISIBILITY);
        assertThat(testSurveyConfiguration.getUsersHavingVisibility()).isEqualTo(UPDATED_USERS_HAVING_VISIBILITY);
        assertThat(testSurveyConfiguration.getGroupsHavingVisibility()).isEqualTo(UPDATED_GROUPS_HAVING_VISIBILITY);
        assertThat(testSurveyConfiguration.getResultVisibility()).isEqualTo(UPDATED_RESULT_VISIBILITY);
        assertThat(testSurveyConfiguration.getUsersHavingResultVisibility()).isEqualTo(UPDATED_USERS_HAVING_RESULT_VISIBILITY);
        assertThat(testSurveyConfiguration.getGroupsHavingResultVisibility()).isEqualTo(UPDATED_GROUPS_HAVING_RESULT_VISIBILITY);
        assertThat(testSurveyConfiguration.getMaintainerUsers()).isEqualTo(UPDATED_MAINTAINER_USERS);
        assertThat(testSurveyConfiguration.getMaintainerUserGroups()).isEqualTo(UPDATED_MAINTAINER_USER_GROUPS);
    }

    @Test
    @Transactional
    public void updateNonExistingSurveyConfiguration() throws Exception {
        int databaseSizeBeforeUpdate = surveyConfigurationRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSurveyConfigurationMockMvc.perform(put("/api/survey-configurations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(surveyConfiguration)))
            .andExpect(status().isBadRequest());

        // Validate the SurveyConfiguration in the database
        List<SurveyConfiguration> surveyConfigurationList = surveyConfigurationRepository.findAll();
        assertThat(surveyConfigurationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSurveyConfiguration() throws Exception {
        // Initialize the database
        surveyConfigurationRepository.saveAndFlush(surveyConfiguration);

        int databaseSizeBeforeDelete = surveyConfigurationRepository.findAll().size();

        // Delete the surveyConfiguration
        restSurveyConfigurationMockMvc.perform(delete("/api/survey-configurations/{id}", surveyConfiguration.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SurveyConfiguration> surveyConfigurationList = surveyConfigurationRepository.findAll();
        assertThat(surveyConfigurationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
