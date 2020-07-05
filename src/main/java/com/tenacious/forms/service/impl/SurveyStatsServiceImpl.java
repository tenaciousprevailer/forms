package com.tenacious.forms.service.impl;

import com.tenacious.forms.service.SurveyStatsService;
import com.tenacious.forms.domain.SurveyStats;
import com.tenacious.forms.repository.SurveyStatsRepository;
import com.tenacious.forms.service.dto.SurveyStatsDTO;
import com.tenacious.forms.service.mapper.SurveyStatsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SurveyStats}.
 */
@Service
@Transactional
public class SurveyStatsServiceImpl implements SurveyStatsService {

    private final Logger log = LoggerFactory.getLogger(SurveyStatsServiceImpl.class);

    private final SurveyStatsRepository surveyStatsRepository;

    private final SurveyStatsMapper surveyStatsMapper;

    public SurveyStatsServiceImpl(SurveyStatsRepository surveyStatsRepository, SurveyStatsMapper surveyStatsMapper) {
        this.surveyStatsRepository = surveyStatsRepository;
        this.surveyStatsMapper = surveyStatsMapper;
    }

    @Override
    public SurveyStatsDTO save(SurveyStatsDTO surveyStatsDTO) {
        log.debug("Request to save SurveyStats : {}", surveyStatsDTO);
        SurveyStats surveyStats = surveyStatsMapper.toEntity(surveyStatsDTO);
        surveyStats = surveyStatsRepository.save(surveyStats);
        return surveyStatsMapper.toDto(surveyStats);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SurveyStatsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SurveyStats");
        return surveyStatsRepository.findAll(pageable)
            .map(surveyStatsMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<SurveyStatsDTO> findOne(Long id) {
        log.debug("Request to get SurveyStats : {}", id);
        return surveyStatsRepository.findById(id)
            .map(surveyStatsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SurveyStats : {}", id);
        surveyStatsRepository.deleteById(id);
    }
}
