package com.tenacious.forms.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SurveyConfigurationMapperTest {

    private SurveyConfigurationMapper surveyConfigurationMapper;

    @BeforeEach
    public void setUp() {
        surveyConfigurationMapper = new SurveyConfigurationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(surveyConfigurationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(surveyConfigurationMapper.fromId(null)).isNull();
    }
}
