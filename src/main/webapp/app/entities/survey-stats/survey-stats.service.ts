import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISurveyStats } from 'app/shared/model/survey-stats.model';

type EntityResponseType = HttpResponse<ISurveyStats>;
type EntityArrayResponseType = HttpResponse<ISurveyStats[]>;

@Injectable({ providedIn: 'root' })
export class SurveyStatsService {
  public resourceUrl = SERVER_API_URL + 'api/survey-stats';

  constructor(protected http: HttpClient) {}

  create(surveyStats: ISurveyStats): Observable<EntityResponseType> {
    return this.http.post<ISurveyStats>(this.resourceUrl, surveyStats, { observe: 'response' });
  }

  update(surveyStats: ISurveyStats): Observable<EntityResponseType> {
    return this.http.put<ISurveyStats>(this.resourceUrl, surveyStats, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISurveyStats>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISurveyStats[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
