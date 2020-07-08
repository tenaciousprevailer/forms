package com.tenacious.forms.service.dto;

import java.io.Serializable;
import com.tenacious.forms.domain.enumeration.QuestionType;

/**
 * A DTO for the {@link com.tenacious.forms.domain.Question} entity.
 */
public class QuestionDTO implements Serializable {
    
    private Long id;

    private String text;

    private QuestionType type;

    private String jsonData;

    private Integer totalResponseCount;


    private Long surveyId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
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
        if (!(o instanceof QuestionDTO)) {
            return false;
        }

        return id != null && id.equals(((QuestionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionDTO{" +
            "id=" + getId() +
            ", text='" + getText() + "'" +
            ", type='" + getType() + "'" +
            ", jsonData='" + getJsonData() + "'" +
            ", totalResponseCount=" + getTotalResponseCount() +
            ", surveyId=" + getSurveyId() +
            "}";
    }
}
