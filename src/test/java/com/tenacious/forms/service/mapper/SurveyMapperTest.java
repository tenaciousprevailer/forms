package com.tenacious.forms.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SurveyMapperTest {

    private SurveyMapper surveyMapper;

    @BeforeEach
    public void setUp() {
        surveyMapper = new SurveyMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(surveyMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(surveyMapper.fromId(null)).isNull();
    }
}
