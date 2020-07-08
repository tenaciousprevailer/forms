import { Moment } from 'moment';
import { IQuestion } from 'app/shared/model/question.model';
import { IAnswer } from 'app/shared/model/answer.model';

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
  totalResponseCount?: number;
  questions?: IQuestion[];
  answers?: IAnswer[];
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
    public totalResponseCount?: number,
    public questions?: IQuestion[],
    public answers?: IAnswer[]
  ) {}
}
