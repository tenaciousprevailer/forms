import { IUserResponse } from 'app/shared/model/user-response.model';
import { QuestionType } from 'app/shared/model/enumerations/question-type.model';

export interface IQuestion {
  id?: number;
  text?: string;
  type?: QuestionType;
  jsonData?: string;
  userResponses?: IUserResponse[];
  surveyId?: number;
}

export class Question implements IQuestion {
  constructor(
    public id?: number,
    public text?: string,
    public type?: QuestionType,
    public jsonData?: string,
    public userResponses?: IUserResponse[],
    public surveyId?: number
  ) {}
}
