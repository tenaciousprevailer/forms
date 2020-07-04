package com.tenacious.forms.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tenacious.forms.web.rest.TestUtil;

public class SurveyConfigurationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SurveyConfiguration.class);
        SurveyConfiguration surveyConfiguration1 = new SurveyConfiguration();
        surveyConfiguration1.setId(1L);
        SurveyConfiguration surveyConfiguration2 = new SurveyConfiguration();
        surveyConfiguration2.setId(surveyConfiguration1.getId());
        assertThat(surveyConfiguration1).isEqualTo(surveyConfiguration2);
        surveyConfiguration2.setId(2L);
        assertThat(surveyConfiguration1).isNotEqualTo(surveyConfiguration2);
        surveyConfiguration1.setId(null);
        assertThat(surveyConfiguration1).isNotEqualTo(surveyConfiguration2);
    }
}
