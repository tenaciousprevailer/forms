import { IAnswer } from 'app/shared/model/answer.model';
import { QuestionType } from 'app/shared/model/enumerations/question-type.model';

export interface IQuestion {
  id?: number;
  text?: string;
  type?: QuestionType;
  jsonData?: string;
  totalResponseCount?: number;
  answers?: IAnswer[];
  surveyId?: number;
}

export class Question implements IQuestion {
  constructor(
    public id?: number,
    public text?: string,
    public type?: QuestionType,
    public jsonData?: string,
    public totalResponseCount?: number,
    public answers?: IAnswer[],
    public surveyId?: number
  ) {}
}
