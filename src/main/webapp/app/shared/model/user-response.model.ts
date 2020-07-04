import { Moment } from 'moment';
import { ISurvey } from 'app/shared/model/survey.model';
import { IQuestion } from 'app/shared/model/question.model';

export interface IUserResponse {
  id?: number;
  dateCreated?: Moment;
  takenBy?: string;
  responseData?: string;
  survey?: ISurvey;
  question?: IQuestion;
}

export class UserResponse implements IUserResponse {
  constructor(
    public id?: number,
    public dateCreated?: Moment,
    public takenBy?: string,
    public responseData?: string,
    public survey?: ISurvey,
    public question?: IQuestion
  ) {}
}
