package com.tenacious.forms.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Answer.
 */
@Entity
@Table(name = "answer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_created")
    private Instant dateCreated;

    @Column(name = "taken_by")
    private String takenBy;

    @Column(name = "answer_data")
    private String answerData;

    @ManyToOne
    @JsonIgnoreProperties(value = "answers", allowSetters = true)
    private Survey survey;

    @ManyToOne
    @JsonIgnoreProperties(value = "answers", allowSetters = true)
    private Question question;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateCreated() {
        return dateCreated;
    }

    public Answer dateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public void setDateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getTakenBy() {
        return takenBy;
    }

    public Answer takenBy(String takenBy) {
        this.takenBy = takenBy;
        return this;
    }

    public void setTakenBy(String takenBy) {
        this.takenBy = takenBy;
    }

    public String getAnswerData() {
        return answerData;
    }

    public Answer answerData(String answerData) {
        this.answerData = answerData;
        return this;
    }

    public void setAnswerData(String answerData) {
        this.answerData = answerData;
    }

    public Survey getSurvey() {
        return survey;
    }

    public Answer survey(Survey survey) {
        this.survey = survey;
        return this;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public Question getQuestion() {
        return question;
    }

    public Answer question(Question question) {
        this.question = question;
        return this;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Answer)) {
            return false;
        }
        return id != null && id.equals(((Answer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Answer{" +
            "id=" + getId() +
            ", dateCreated='" + getDateCreated() + "'" +
            ", takenBy='" + getTakenBy() + "'" +
            ", answerData='" + getAnswerData() + "'" +
            "}";
    }
}
