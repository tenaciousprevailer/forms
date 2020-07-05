package com.tenacious.forms.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.tenacious.forms.domain.SurveyStats} entity.
 */
public class SurveyStatsDTO implements Serializable {
    
    private Long id;

    private Integer totalResponseCount;


    private Long surveyId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotalResponseCount() {
        return totalResponseCount;
    }

    public void setTotalResponseCount(Integer totalResponseCount) {
        this.totalResponseCount = totalResponseCount;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SurveyStatsDTO)) {
            return false;
        }

        return id != null && id.equals(((SurveyStatsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SurveyStatsDTO{" +
            "id=" + getId() +
            ", totalResponseCount=" + getTotalResponseCount() +
            ", surveyId=" + getSurveyId() +
            "}";
    }
}
