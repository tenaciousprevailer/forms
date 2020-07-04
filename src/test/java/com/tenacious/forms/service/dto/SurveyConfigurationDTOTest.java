package com.tenacious.forms.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tenacious.forms.web.rest.TestUtil;

public class SurveyConfigurationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SurveyConfigurationDTO.class);
        SurveyConfigurationDTO surveyConfigurationDTO1 = new SurveyConfigurationDTO();
        surveyConfigurationDTO1.setId(1L);
        SurveyConfigurationDTO surveyConfigurationDTO2 = new SurveyConfigurationDTO();
        assertThat(surveyConfigurationDTO1).isNotEqualTo(surveyConfigurationDTO2);
        surveyConfigurationDTO2.setId(surveyConfigurationDTO1.getId());
        assertThat(surveyConfigurationDTO1).isEqualTo(surveyConfigurationDTO2);
        surveyConfigurationDTO2.setId(2L);
        assertThat(surveyConfigurationDTO1).isNotEqualTo(surveyConfigurationDTO2);
        surveyConfigurationDTO1.setId(null);
        assertThat(surveyConfigurationDTO1).isNotEqualTo(surveyConfigurationDTO2);
    }
}
