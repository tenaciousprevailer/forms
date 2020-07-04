package com.tenacious.forms.web.rest;

import com.tenacious.forms.FormsApp;
import com.tenacious.forms.domain.SurveyStats;
import com.tenacious.forms.repository.SurveyStatsRepository;
import com.tenacious.forms.service.SurveyStatsService;
import com.tenacious.forms.service.dto.SurveyStatsDTO;
import com.tenacious.forms.service.mapper.SurveyStatsMapper;

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

/**
 * Integration tests for the {@link SurveyStatsResource} REST controller.
 */
@SpringBootTest(classes = FormsApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SurveyStatsResourceIT {

    private static final Integer DEFAULT_TOTAL_RESPONSE_COUNT = 1;
    private static final Integer UPDATED_TOTAL_RESPONSE_COUNT = 2;

    @Autowired
    private SurveyStatsRepository surveyStatsRepository;

    @Autowired
    private SurveyStatsMapper surveyStatsMapper;

    @Autowired
    private SurveyStatsService surveyStatsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSurveyStatsMockMvc;

    private SurveyStats surveyStats;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SurveyStats createEntity(EntityManager em) {
        SurveyStats surveyStats = new SurveyStats()
            .totalResponseCount(DEFAULT_TOTAL_RESPONSE_COUNT);
        return surveyStats;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SurveyStats createUpdatedEntity(EntityManager em) {
        SurveyStats surveyStats = new SurveyStats()
            .totalResponseCount(UPDATED_TOTAL_RESPONSE_COUNT);
        return surveyStats;
    }

    @BeforeEach
    public void initTest() {
        surveyStats = createEntity(em);
    }

    @Test
    @Transactional
    public void createSurveyStats() throws Exception {
        int databaseSizeBeforeCreate = surveyStatsRepository.findAll().size();
        // Create the SurveyStats
        SurveyStatsDTO surveyStatsDTO = surveyStatsMapper.toDto(surveyStats);
        restSurveyStatsMockMvc.perform(post("/api/survey-stats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(surveyStatsDTO)))
            .andExpect(status().isCreated());

        // Validate the SurveyStats in the database
        List<SurveyStats> surveyStatsList = surveyStatsRepository.findAll();
        assertThat(surveyStatsList).hasSize(databaseSizeBeforeCreate + 1);
        SurveyStats testSurveyStats = surveyStatsList.get(surveyStatsList.size() - 1);
        assertThat(testSurveyStats.getTotalResponseCount()).isEqualTo(DEFAULT_TOTAL_RESPONSE_COUNT);
    }

    @Test
    @Transactional
    public void createSurveyStatsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = surveyStatsRepository.findAll().size();

        // Create the SurveyStats with an existing ID
        surveyStats.setId(1L);
        SurveyStatsDTO surveyStatsDTO = surveyStatsMapper.toDto(surveyStats);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSurveyStatsMockMvc.perform(post("/api/survey-stats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(surveyStatsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SurveyStats in the database
        List<SurveyStats> surveyStatsList = surveyStatsRepository.findAll();
        assertThat(surveyStatsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSurveyStats() throws Exception {
        // Initialize the database
        surveyStatsRepository.saveAndFlush(surveyStats);

        // Get all the surveyStatsList
        restSurveyStatsMockMvc.perform(get("/api/survey-stats?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(surveyStats.getId().intValue())))
            .andExpect(jsonPath("$.[*].totalResponseCount").value(hasItem(DEFAULT_TOTAL_RESPONSE_COUNT)));
    }
    
    @Test
    @Transactional
    public void getSurveyStats() throws Exception {
        // Initialize the database
        surveyStatsRepository.saveAndFlush(surveyStats);

        // Get the surveyStats
        restSurveyStatsMockMvc.perform(get("/api/survey-stats/{id}", surveyStats.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(surveyStats.getId().intValue()))
            .andExpect(jsonPath("$.totalResponseCount").value(DEFAULT_TOTAL_RESPONSE_COUNT));
    }
    @Test
    @Transactional
    public void getNonExistingSurveyStats() throws Exception {
        // Get the surveyStats
        restSurveyStatsMockMvc.perform(get("/api/survey-stats/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSurveyStats() throws Exception {
        // Initialize the database
        surveyStatsRepository.saveAndFlush(surveyStats);

        int databaseSizeBeforeUpdate = surveyStatsRepository.findAll().size();

        // Update the surveyStats
        SurveyStats updatedSurveyStats = surveyStatsRepository.findById(surveyStats.getId()).get();
        // Disconnect from session so that the updates on updatedSurveyStats are not directly saved in db
        em.detach(updatedSurveyStats);
        updatedSurveyStats
            .totalResponseCount(UPDATED_TOTAL_RESPONSE_COUNT);
        SurveyStatsDTO surveyStatsDTO = surveyStatsMapper.toDto(updatedSurveyStats);

        restSurveyStatsMockMvc.perform(put("/api/survey-stats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(surveyStatsDTO)))
            .andExpect(status().isOk());

        // Validate the SurveyStats in the database
        List<SurveyStats> surveyStatsList = surveyStatsRepository.findAll();
        assertThat(surveyStatsList).hasSize(databaseSizeBeforeUpdate);
        SurveyStats testSurveyStats = surveyStatsList.get(surveyStatsList.size() - 1);
        assertThat(testSurveyStats.getTotalResponseCount()).isEqualTo(UPDATED_TOTAL_RESPONSE_COUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingSurveyStats() throws Exception {
        int databaseSizeBeforeUpdate = surveyStatsRepository.findAll().size();

        // Create the SurveyStats
        SurveyStatsDTO surveyStatsDTO = surveyStatsMapper.toDto(surveyStats);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSurveyStatsMockMvc.perform(put("/api/survey-stats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(surveyStatsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SurveyStats in the database
        List<SurveyStats> surveyStatsList = surveyStatsRepository.findAll();
        assertThat(surveyStatsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSurveyStats() throws Exception {
        // Initialize the database
        surveyStatsRepository.saveAndFlush(surveyStats);

        int databaseSizeBeforeDelete = surveyStatsRepository.findAll().size();

        // Delete the surveyStats
        restSurveyStatsMockMvc.perform(delete("/api/survey-stats/{id}", surveyStats.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SurveyStats> surveyStatsList = surveyStatsRepository.findAll();
        assertThat(surveyStatsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
