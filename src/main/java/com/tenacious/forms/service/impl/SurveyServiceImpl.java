package com.tenacious.forms.service.impl;

import com.tenacious.forms.service.SurveyService;
import com.tenacious.forms.domain.Survey;
import com.tenacious.forms.repository.SurveyRepository;
import com.tenacious.forms.service.dto.SurveyDTO;
import com.tenacious.forms.service.mapper.SurveyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link Survey}.
 */
@Service
@Transactional
public class SurveyServiceImpl implements SurveyService {

    private final Logger log = LoggerFactory.getLogger(SurveyServiceImpl.class);

    private final SurveyRepository surveyRepository;

    private final SurveyMapper surveyMapper;

    public SurveyServiceImpl(SurveyRepository surveyRepository, SurveyMapper surveyMapper) {
        this.surveyRepository = surveyRepository;
        this.surveyMapper = surveyMapper;
    }

    @Override
    public SurveyDTO save(SurveyDTO surveyDTO) {
        log.debug("Request to save Survey : {}", surveyDTO);
        Survey survey = surveyMapper.toEntity(surveyDTO);
        survey = surveyRepository.save(survey);
        return surveyMapper.toDto(survey);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SurveyDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Surveys");
        return surveyRepository.findAll(pageable)
            .map(surveyMapper::toDto);
    }



    /**
     *  Get all the surveys where SurveyConfiguration is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<SurveyDTO> findAllWhereSurveyConfigurationIsNull() {
        log.debug("Request to get all surveys where SurveyConfiguration is null");
        return StreamSupport
            .stream(surveyRepository.findAll().spliterator(), false)
            .filter(survey -> survey.getSurveyConfiguration() == null)
            .map(surveyMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  Get all the surveys where SurveyStats is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<SurveyDTO> findAllWhereSurveyStatsIsNull() {
        log.debug("Request to get all surveys where SurveyStats is null");
        return StreamSupport
            .stream(surveyRepository.findAll().spliterator(), false)
            .filter(survey -> survey.getSurveyStats() == null)
            .map(surveyMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SurveyDTO> findOne(Long id) {
        log.debug("Request to get Survey : {}", id);
        return surveyRepository.findById(id)
            .map(surveyMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Survey : {}", id);
        surveyRepository.deleteById(id);
    }
}
