import { Moment } from 'moment';

export interface IAnswer {
  id?: number;
  dateCreated?: Moment;
  takenBy?: string;
  answerData?: string;
  surveyId?: number;
  questionId?: number;
}

export class Answer implements IAnswer {
  constructor(
    public id?: number,
    public dateCreated?: Moment,
    public takenBy?: string,
    public answerData?: string,
    public surveyId?: number,
    public questionId?: number
  ) {}
}
