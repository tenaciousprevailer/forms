package com.tenacious.forms.service;

import com.tenacious.forms.service.dto.SurveyDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.tenacious.forms.domain.Survey}.
 */
public interface SurveyService {

    /**
     * Save a survey.
     *
     * @param surveyDTO the entity to save.
     * @return the persisted entity.
     */
    SurveyDTO save(SurveyDTO surveyDTO);

    /**
     * Get all the surveys.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SurveyDTO> findAll(Pageable pageable);
    /**
     * Get all the SurveyDTO where SurveyConfiguration is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<SurveyDTO> findAllWhereSurveyConfigurationIsNull();
    /**
     * Get all the SurveyDTO where SurveyStats is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<SurveyDTO> findAllWhereSurveyStatsIsNull();


    /**
     * Get the "id" survey.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SurveyDTO> findOne(Long id);

    /**
     * Delete the "id" survey.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
