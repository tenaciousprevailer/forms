export interface ISurveyStats {
  id?: number;
  totalResponseCount?: number;
  surveyId?: number;
}

export class SurveyStats implements ISurveyStats {
  constructor(public id?: number, public totalResponseCount?: number, public surveyId?: number) {}
}
