import { IUserResponse } from 'app/shared/model/user-response.model';
import { ISurvey } from 'app/shared/model/survey.model';
import { QuestionType } from 'app/shared/model/enumerations/question-type.model';

export interface IQuestion {
  id?: number;
  text?: string;
  type?: QuestionType;
  jsonData?: string;
  userResponses?: IUserResponse[];
  survey?: ISurvey;
}

export class Question implements IQuestion {
  constructor(
    public id?: number,
    public text?: string,
    public type?: QuestionType,
    public jsonData?: string,
    public userResponses?: IUserResponse[],
    public survey?: ISurvey
  ) {}
}
