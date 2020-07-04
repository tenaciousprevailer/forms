package com.tenacious.forms.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tenacious.forms.web.rest.TestUtil;

public class SurveyStatsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SurveyStats.class);
        SurveyStats surveyStats1 = new SurveyStats();
        surveyStats1.setId(1L);
        SurveyStats surveyStats2 = new SurveyStats();
        surveyStats2.setId(surveyStats1.getId());
        assertThat(surveyStats1).isEqualTo(surveyStats2);
        surveyStats2.setId(2L);
        assertThat(surveyStats1).isNotEqualTo(surveyStats2);
        surveyStats1.setId(null);
        assertThat(surveyStats1).isNotEqualTo(surveyStats2);
    }
}
