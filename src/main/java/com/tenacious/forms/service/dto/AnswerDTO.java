package com.tenacious.forms.service.dto;

import java.time.Instant;
import java.io.Serializable;

/**
 * A DTO for the {@link com.tenacious.forms.domain.Answer} entity.
 */
public class AnswerDTO implements Serializable {
    
    private Long id;

    private Instant dateCreated;

    private String takenBy;

    private String answerData;


    private Long surveyId;

    private Long questionId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getTakenBy() {
        return takenBy;
    }

    public void setTakenBy(String takenBy) {
        this.takenBy = takenBy;
    }

    public String getAnswerData() {
        return answerData;
    }

    public void setAnswerData(String answerData) {
        this.answerData = answerData;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnswerDTO)) {
            return false;
        }

        return id != null && id.equals(((AnswerDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnswerDTO{" +
            "id=" + getId() +
            ", dateCreated='" + getDateCreated() + "'" +
            ", takenBy='" + getTakenBy() + "'" +
            ", answerData='" + getAnswerData() + "'" +
            ", surveyId=" + getSurveyId() +
            ", questionId=" + getQuestionId() +
            "}";
    }
}
