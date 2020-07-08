package com.tenacious.forms.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Survey.
 */
@Entity
@Table(name = "survey")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Survey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "date_start")
    private Instant dateStart;

    @Column(name = "date_end")
    private Instant dateEnd;

    @Column(name = "date_created")
    private Instant dateCreated;

    @Column(name = "date_last_updated")
    private Instant dateLastUpdated;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @Column(name = "total_response_count")
    private Integer totalResponseCount;

    @OneToMany(mappedBy = "survey")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Question> questions = new HashSet<>();

    @OneToMany(mappedBy = "survey")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Answer> answers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Survey name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Survey description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getDateStart() {
        return dateStart;
    }

    public Survey dateStart(Instant dateStart) {
        this.dateStart = dateStart;
        return this;
    }

    public void setDateStart(Instant dateStart) {
        this.dateStart = dateStart;
    }

    public Instant getDateEnd() {
        return dateEnd;
    }

    public Survey dateEnd(Instant dateEnd) {
        this.dateEnd = dateEnd;
        return this;
    }

    public void setDateEnd(Instant dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Instant getDateCreated() {
        return dateCreated;
    }

    public Survey dateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public void setDateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Instant getDateLastUpdated() {
        return dateLastUpdated;
    }

    public Survey dateLastUpdated(Instant dateLastUpdated) {
        this.dateLastUpdated = dateLastUpdated;
        return this;
    }

    public void setDateLastUpdated(Instant dateLastUpdated) {
        this.dateLastUpdated = dateLastUpdated;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Survey createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public Survey lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Integer getTotalResponseCount() {
        return totalResponseCount;
    }

    public Survey totalResponseCount(Integer totalResponseCount) {
        this.totalResponseCount = totalResponseCount;
        return this;
    }

    public void setTotalResponseCount(Integer totalResponseCount) {
        this.totalResponseCount = totalResponseCount;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public Survey questions(Set<Question> questions) {
        this.questions = questions;
        return this;
    }

    public Survey addQuestion(Question question) {
        this.questions.add(question);
        question.setSurvey(this);
        return this;
    }

    public Survey removeQuestion(Question question) {
        this.questions.remove(question);
        question.setSurvey(null);
        return this;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public Survey answers(Set<Answer> answers) {
        this.answers = answers;
        return this;
    }

    public Survey addAnswer(Answer answer) {
        this.answers.add(answer);
        answer.setSurvey(this);
        return this;
    }

    public Survey removeAnswer(Answer answer) {
        this.answers.remove(answer);
        answer.setSurvey(null);
        return this;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Survey)) {
            return false;
        }
        return id != null && id.equals(((Survey) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Survey{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", dateStart='" + getDateStart() + "'" +
            ", dateEnd='" + getDateEnd() + "'" +
            ", dateCreated='" + getDateCreated() + "'" +
            ", dateLastUpdated='" + getDateLastUpdated() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", totalResponseCount=" + getTotalResponseCount() +
            "}";
    }
}
