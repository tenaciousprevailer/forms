package com.tenacious.forms.service;

import com.tenacious.forms.service.dto.SurveyStatsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.tenacious.forms.domain.SurveyStats}.
 */
public interface SurveyStatsService {

    /**
     * Save a surveyStats.
     *
     * @param surveyStatsDTO the entity to save.
     * @return the persisted entity.
     */
    SurveyStatsDTO save(SurveyStatsDTO surveyStatsDTO);

    /**
     * Get all the surveyStats.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SurveyStatsDTO> findAll(Pageable pageable);
    /**
     * Get all the SurveyStatsDTO where Survey is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<SurveyStatsDTO> findAllWhereSurveyIsNull();


    /**
     * Get the "id" surveyStats.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SurveyStatsDTO> findOne(Long id);

    /**
     * Delete the "id" surveyStats.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
