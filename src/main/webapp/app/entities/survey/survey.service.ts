import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISurvey } from 'app/shared/model/survey.model';

type EntityResponseType = HttpResponse<ISurvey>;
type EntityArrayResponseType = HttpResponse<ISurvey[]>;

@Injectable({ providedIn: 'root' })
export class SurveyService {
  public resourceUrl = SERVER_API_URL + 'api/surveys';

  constructor(protected http: HttpClient) {}

  create(survey: ISurvey): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(survey);
    return this.http
      .post<ISurvey>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(survey: ISurvey): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(survey);
    return this.http
      .put<ISurvey>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ISurvey>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISurvey[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(survey: ISurvey): ISurvey {
    const copy: ISurvey = Object.assign({}, survey, {
      dateStart: survey.dateStart && survey.dateStart.isValid() ? survey.dateStart.toJSON() : undefined,
      dateEnd: survey.dateEnd && survey.dateEnd.isValid() ? survey.dateEnd.toJSON() : undefined,
      dateCreated: survey.dateCreated && survey.dateCreated.isValid() ? survey.dateCreated.toJSON() : undefined,
      dateLastUpdated: survey.dateLastUpdated && survey.dateLastUpdated.isValid() ? survey.dateLastUpdated.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateStart = res.body.dateStart ? moment(res.body.dateStart) : undefined;
      res.body.dateEnd = res.body.dateEnd ? moment(res.body.dateEnd) : undefined;
      res.body.dateCreated = res.body.dateCreated ? moment(res.body.dateCreated) : undefined;
      res.body.dateLastUpdated = res.body.dateLastUpdated ? moment(res.body.dateLastUpdated) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((survey: ISurvey) => {
        survey.dateStart = survey.dateStart ? moment(survey.dateStart) : undefined;
        survey.dateEnd = survey.dateEnd ? moment(survey.dateEnd) : undefined;
        survey.dateCreated = survey.dateCreated ? moment(survey.dateCreated) : undefined;
        survey.dateLastUpdated = survey.dateLastUpdated ? moment(survey.dateLastUpdated) : undefined;
      });
    }
    return res;
  }
}
