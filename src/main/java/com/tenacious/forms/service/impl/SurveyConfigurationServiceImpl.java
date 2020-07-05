package com.tenacious.forms.service.impl;

import com.tenacious.forms.service.SurveyConfigurationService;
import com.tenacious.forms.domain.SurveyConfiguration;
import com.tenacious.forms.repository.SurveyConfigurationRepository;
import com.tenacious.forms.service.dto.SurveyConfigurationDTO;
import com.tenacious.forms.service.mapper.SurveyConfigurationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SurveyConfiguration}.
 */
@Service
@Transactional
public class SurveyConfigurationServiceImpl implements SurveyConfigurationService {

    private final Logger log = LoggerFactory.getLogger(SurveyConfigurationServiceImpl.class);

    private final SurveyConfigurationRepository surveyConfigurationRepository;

    private final SurveyConfigurationMapper surveyConfigurationMapper;

    public SurveyConfigurationServiceImpl(SurveyConfigurationRepository surveyConfigurationRepository, SurveyConfigurationMapper surveyConfigurationMapper) {
        this.surveyConfigurationRepository = surveyConfigurationRepository;
        this.surveyConfigurationMapper = surveyConfigurationMapper;
    }

    @Override
    public SurveyConfigurationDTO save(SurveyConfigurationDTO surveyConfigurationDTO) {
        log.debug("Request to save SurveyConfiguration : {}", surveyConfigurationDTO);
        SurveyConfiguration surveyConfiguration = surveyConfigurationMapper.toEntity(surveyConfigurationDTO);
        surveyConfiguration = surveyConfigurationRepository.save(surveyConfiguration);
        return surveyConfigurationMapper.toDto(surveyConfiguration);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SurveyConfigurationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SurveyConfigurations");
        return surveyConfigurationRepository.findAll(pageable)
            .map(surveyConfigurationMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<SurveyConfigurationDTO> findOne(Long id) {
        log.debug("Request to get SurveyConfiguration : {}", id);
        return surveyConfigurationRepository.findById(id)
            .map(surveyConfigurationMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SurveyConfiguration : {}", id);
        surveyConfigurationRepository.deleteById(id);
    }
}
