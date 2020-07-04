import { ISurvey } from 'app/shared/model/survey.model';
import { VisibilityType } from 'app/shared/model/enumerations/visibility-type.model';

export interface ISurveyConfiguration {
  id?: number;
  surveyVisibility?: VisibilityType;
  usersHavingVisibility?: string;
  groupsHavingVisibility?: string;
  resultVisibility?: VisibilityType;
  usersHavingResultVisibility?: string;
  groupsHavingResultVisibility?: string;
  maintainerUsers?: string;
  maintainerUserGroups?: string;
  survey?: ISurvey;
}

export class SurveyConfiguration implements ISurveyConfiguration {
  constructor(
    public id?: number,
    public surveyVisibility?: VisibilityType,
    public usersHavingVisibility?: string,
    public groupsHavingVisibility?: string,
    public resultVisibility?: VisibilityType,
    public usersHavingResultVisibility?: string,
    public groupsHavingResultVisibility?: string,
    public maintainerUsers?: string,
    public maintainerUserGroups?: string,
    public survey?: ISurvey
  ) {}
}
