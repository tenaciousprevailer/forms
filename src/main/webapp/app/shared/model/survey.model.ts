import { Moment } from 'moment';
import { IQuestion } from 'app/shared/model/question.model';
import { IUserResponse } from 'app/shared/model/user-response.model';
import { SurveyStatus } from 'app/shared/model/enumerations/survey-status.model';

export interface ISurvey {
  id?: number;
  name?: string;
  description?: string;
  dateStart?: Moment;
  dateEnd?: Moment;
  dateCreated?: Moment;
  dateLastUpdated?: Moment;
  createdBy?: string;
  lastUpdatedBy?: string;
  status?: SurveyStatus;
  questions?: IQuestion[];
  userResponses?: IUserResponse[];
  surveyConfigurationId?: number;
  surveyStatsId?: number;
}

export class Survey implements ISurvey {
  constructor(
    public id?: number,
    public name?: string,
    public description?: string,
    public dateStart?: Moment,
    public dateEnd?: Moment,
    public dateCreated?: Moment,
    public dateLastUpdated?: Moment,
    public createdBy?: string,
    public lastUpdatedBy?: string,
    public status?: SurveyStatus,
    public questions?: IQuestion[],
    public userResponses?: IUserResponse[],
    public surveyConfigurationId?: number,
    public surveyStatsId?: number
  ) {}
}
