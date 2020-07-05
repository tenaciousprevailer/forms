package com.tenacious.forms.service;

import com.tenacious.forms.service.dto.SurveyConfigurationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.tenacious.forms.domain.SurveyConfiguration}.
 */
public interface SurveyConfigurationService {

    /**
     * Save a surveyConfiguration.
     *
     * @param surveyConfigurationDTO the entity to save.
     * @return the persisted entity.
     */
    SurveyConfigurationDTO save(SurveyConfigurationDTO surveyConfigurationDTO);

    /**
     * Get all the surveyConfigurations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SurveyConfigurationDTO> findAll(Pageable pageable);


    /**
     * Get the "id" surveyConfiguration.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SurveyConfigurationDTO> findOne(Long id);

    /**
     * Delete the "id" surveyConfiguration.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
