import { Moment } from 'moment';

export interface IUserResponse {
  id?: number;
  dateCreated?: Moment;
  takenBy?: string;
  responseData?: string;
  surveyId?: number;
  questionId?: number;
}

export class UserResponse implements IUserResponse {
  constructor(
    public id?: number,
    public dateCreated?: Moment,
    public takenBy?: string,
    public responseData?: string,
    public surveyId?: number,
    public questionId?: number
  ) {}
}
