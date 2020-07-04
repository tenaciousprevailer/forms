package com.tenacious.forms.service.dto;

import java.time.Instant;
import java.io.Serializable;

/**
 * A DTO for the {@link com.tenacious.forms.domain.UserResponse} entity.
 */
public class UserResponseDTO implements Serializable {
    
    private Long id;

    private Instant dateCreated;

    private String takenBy;

    private String responseData;


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

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
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
        if (!(o instanceof UserResponseDTO)) {
            return false;
        }

        return id != null && id.equals(((UserResponseDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserResponseDTO{" +
            "id=" + getId() +
            ", dateCreated='" + getDateCreated() + "'" +
            ", takenBy='" + getTakenBy() + "'" +
            ", responseData='" + getResponseData() + "'" +
            ", surveyId=" + getSurveyId() +
            ", questionId=" + getQuestionId() +
            "}";
    }
}
