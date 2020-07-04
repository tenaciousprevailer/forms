package com.tenacious.forms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A SurveyStats.
 */
@Entity
@Table(name = "survey_stats")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SurveyStats implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_response_count")
    private Integer totalResponseCount;

    @OneToOne(mappedBy = "surveyStats")
    @JsonIgnore
    private Survey survey;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotalResponseCount() {
        return totalResponseCount;
    }

    public SurveyStats totalResponseCount(Integer totalResponseCount) {
        this.totalResponseCount = totalResponseCount;
        return this;
    }

    public void setTotalResponseCount(Integer totalResponseCount) {
        this.totalResponseCount = totalResponseCount;
    }

    public Survey getSurvey() {
        return survey;
    }

    public SurveyStats survey(Survey survey) {
        this.survey = survey;
        return this;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SurveyStats)) {
            return false;
        }
        return id != null && id.equals(((SurveyStats) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SurveyStats{" +
            "id=" + getId() +
            ", totalResponseCount=" + getTotalResponseCount() +
            "}";
    }
}
