package com.tenacious.forms.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SurveyStatsMapperTest {

    private SurveyStatsMapper surveyStatsMapper;

    @BeforeEach
    public void setUp() {
        surveyStatsMapper = new SurveyStatsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(surveyStatsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(surveyStatsMapper.fromId(null)).isNull();
    }
}
