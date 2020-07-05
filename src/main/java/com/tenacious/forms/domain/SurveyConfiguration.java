package com.tenacious.forms.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

import com.tenacious.forms.domain.enumeration.VisibilityType;

/**
 * A SurveyConfiguration.
 */
@Entity
@Table(name = "survey_configuration")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SurveyConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "survey_visibility")
    private VisibilityType surveyVisibility;

    @Column(name = "users_having_visibility")
    private String usersHavingVisibility;

    @Column(name = "groups_having_visibility")
    private String groupsHavingVisibility;

    @Enumerated(EnumType.STRING)
    @Column(name = "result_visibility")
    private VisibilityType resultVisibility;

    @Column(name = "users_having_result_visibility")
    private String usersHavingResultVisibility;

    @Column(name = "groups_having_result_visibility")
    private String groupsHavingResultVisibility;

    @Column(name = "maintainer_users")
    private String maintainerUsers;

    @Column(name = "maintainer_user_groups")
    private String maintainerUserGroups;

    @OneToOne
    @JoinColumn(unique = true)
    private Survey survey;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VisibilityType getSurveyVisibility() {
        return surveyVisibility;
    }

    public SurveyConfiguration surveyVisibility(VisibilityType surveyVisibility) {
        this.surveyVisibility = surveyVisibility;
        return this;
    }

    public void setSurveyVisibility(VisibilityType surveyVisibility) {
        this.surveyVisibility = surveyVisibility;
    }

    public String getUsersHavingVisibility() {
        return usersHavingVisibility;
    }

    public SurveyConfiguration usersHavingVisibility(String usersHavingVisibility) {
        this.usersHavingVisibility = usersHavingVisibility;
        return this;
    }

    public void setUsersHavingVisibility(String usersHavingVisibility) {
        this.usersHavingVisibility = usersHavingVisibility;
    }

    public String getGroupsHavingVisibility() {
        return groupsHavingVisibility;
    }

    public SurveyConfiguration groupsHavingVisibility(String groupsHavingVisibility) {
        this.groupsHavingVisibility = groupsHavingVisibility;
        return this;
    }

    public void setGroupsHavingVisibility(String groupsHavingVisibility) {
        this.groupsHavingVisibility = groupsHavingVisibility;
    }

    public VisibilityType getResultVisibility() {
        return resultVisibility;
    }

    public SurveyConfiguration resultVisibility(VisibilityType resultVisibility) {
        this.resultVisibility = resultVisibility;
        return this;
    }

    public void setResultVisibility(VisibilityType resultVisibility) {
        this.resultVisibility = resultVisibility;
    }

    public String getUsersHavingResultVisibility() {
        return usersHavingResultVisibility;
    }

    public SurveyConfiguration usersHavingResultVisibility(String usersHavingResultVisibility) {
        this.usersHavingResultVisibility = usersHavingResultVisibility;
        return this;
    }

    public void setUsersHavingResultVisibility(String usersHavingResultVisibility) {
        this.usersHavingResultVisibility = usersHavingResultVisibility;
    }

    public String getGroupsHavingResultVisibility() {
        return groupsHavingResultVisibility;
    }

    public SurveyConfiguration groupsHavingResultVisibility(String groupsHavingResultVisibility) {
        this.groupsHavingResultVisibility = groupsHavingResultVisibility;
        return this;
    }

    public void setGroupsHavingResultVisibility(String groupsHavingResultVisibility) {
        this.groupsHavingResultVisibility = groupsHavingResultVisibility;
    }

    public String getMaintainerUsers() {
        return maintainerUsers;
    }

    public SurveyConfiguration maintainerUsers(String maintainerUsers) {
        this.maintainerUsers = maintainerUsers;
        return this;
    }

    public void setMaintainerUsers(String maintainerUsers) {
        this.maintainerUsers = maintainerUsers;
    }

    public String getMaintainerUserGroups() {
        return maintainerUserGroups;
    }

    public SurveyConfiguration maintainerUserGroups(String maintainerUserGroups) {
        this.maintainerUserGroups = maintainerUserGroups;
        return this;
    }

    public void setMaintainerUserGroups(String maintainerUserGroups) {
        this.maintainerUserGroups = maintainerUserGroups;
    }

    public Survey getSurvey() {
        return survey;
    }

    public SurveyConfiguration survey(Survey survey) {
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
        if (!(o instanceof SurveyConfiguration)) {
            return false;
        }
        return id != null && id.equals(((SurveyConfiguration) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SurveyConfiguration{" +
            "id=" + getId() +
            ", surveyVisibility='" + getSurveyVisibility() + "'" +
            ", usersHavingVisibility='" + getUsersHavingVisibility() + "'" +
            ", groupsHavingVisibility='" + getGroupsHavingVisibility() + "'" +
            ", resultVisibility='" + getResultVisibility() + "'" +
            ", usersHavingResultVisibility='" + getUsersHavingResultVisibility() + "'" +
            ", groupsHavingResultVisibility='" + getGroupsHavingResultVisibility() + "'" +
            ", maintainerUsers='" + getMaintainerUsers() + "'" +
            ", maintainerUserGroups='" + getMaintainerUserGroups() + "'" +
            "}";
    }
}
