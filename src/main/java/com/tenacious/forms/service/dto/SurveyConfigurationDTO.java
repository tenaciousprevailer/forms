package com.tenacious.forms.service.dto;

import java.io.Serializable;
import com.tenacious.forms.domain.enumeration.VisibilityType;

/**
 * A DTO for the {@link com.tenacious.forms.domain.SurveyConfiguration} entity.
 */
public class SurveyConfigurationDTO implements Serializable {
    
    private Long id;

    private VisibilityType surveyVisibility;

    private String usersHavingVisibility;

    private String groupsHavingVisibility;

    private VisibilityType resultVisibility;

    private String usersHavingResultVisibility;

    private String groupsHavingResultVisibility;

    private String maintainerUsers;

    private String maintainerUserGroups;


    private Long surveyId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VisibilityType getSurveyVisibility() {
        return surveyVisibility;
    }

    public void setSurveyVisibility(VisibilityType surveyVisibility) {
        this.surveyVisibility = surveyVisibility;
    }

    public String getUsersHavingVisibility() {
        return usersHavingVisibility;
    }

    public void setUsersHavingVisibility(String usersHavingVisibility) {
        this.usersHavingVisibility = usersHavingVisibility;
    }

    public String getGroupsHavingVisibility() {
        return groupsHavingVisibility;
    }

    public void setGroupsHavingVisibility(String groupsHavingVisibility) {
        this.groupsHavingVisibility = groupsHavingVisibility;
    }

    public VisibilityType getResultVisibility() {
        return resultVisibility;
    }

    public void setResultVisibility(VisibilityType resultVisibility) {
        this.resultVisibility = resultVisibility;
    }

    public String getUsersHavingResultVisibility() {
        return usersHavingResultVisibility;
    }

    public void setUsersHavingResultVisibility(String usersHavingResultVisibility) {
        this.usersHavingResultVisibility = usersHavingResultVisibility;
    }

    public String getGroupsHavingResultVisibility() {
        return groupsHavingResultVisibility;
    }

    public void setGroupsHavingResultVisibility(String groupsHavingResultVisibility) {
        this.groupsHavingResultVisibility = groupsHavingResultVisibility;
    }

    public String getMaintainerUsers() {
        return maintainerUsers;
    }

    public void setMaintainerUsers(String maintainerUsers) {
        this.maintainerUsers = maintainerUsers;
    }

    public String getMaintainerUserGroups() {
        return maintainerUserGroups;
    }

    public void setMaintainerUserGroups(String maintainerUserGroups) {
        this.maintainerUserGroups = maintainerUserGroups;
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
        if (!(o instanceof SurveyConfigurationDTO)) {
            return false;
        }

        return id != null && id.equals(((SurveyConfigurationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SurveyConfigurationDTO{" +
            "id=" + getId() +
            ", surveyVisibility='" + getSurveyVisibility() + "'" +
            ", usersHavingVisibility='" + getUsersHavingVisibility() + "'" +
            ", groupsHavingVisibility='" + getGroupsHavingVisibility() + "'" +
            ", resultVisibility='" + getResultVisibility() + "'" +
            ", usersHavingResultVisibility='" + getUsersHavingResultVisibility() + "'" +
            ", groupsHavingResultVisibility='" + getGroupsHavingResultVisibility() + "'" +
            ", maintainerUsers='" + getMaintainerUsers() + "'" +
            ", maintainerUserGroups='" + getMaintainerUserGroups() + "'" +
            ", surveyId=" + getSurveyId() +
            "}";
    }
}
