package com.tenacious.forms.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tenacious.forms.web.rest.TestUtil;

public class SurveyStatsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SurveyStatsDTO.class);
        SurveyStatsDTO surveyStatsDTO1 = new SurveyStatsDTO();
        surveyStatsDTO1.setId(1L);
        SurveyStatsDTO surveyStatsDTO2 = new SurveyStatsDTO();
        assertThat(surveyStatsDTO1).isNotEqualTo(surveyStatsDTO2);
        surveyStatsDTO2.setId(surveyStatsDTO1.getId());
        assertThat(surveyStatsDTO1).isEqualTo(surveyStatsDTO2);
        surveyStatsDTO2.setId(2L);
        assertThat(surveyStatsDTO1).isNotEqualTo(surveyStatsDTO2);
        surveyStatsDTO1.setId(null);
        assertThat(surveyStatsDTO1).isNotEqualTo(surveyStatsDTO2);
    }
}
