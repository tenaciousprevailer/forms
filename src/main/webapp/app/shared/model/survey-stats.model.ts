import { ISurvey } from 'app/shared/model/survey.model';

export interface ISurveyStats {
  id?: number;
  totalResponseCount?: number;
  survey?: ISurvey;
}

export class SurveyStats implements ISurveyStats {
  constructor(public id?: number, public totalResponseCount?: number, public survey?: ISurvey) {}
}
