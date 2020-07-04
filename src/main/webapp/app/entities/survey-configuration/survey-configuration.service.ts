import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISurveyConfiguration } from 'app/shared/model/survey-configuration.model';

type EntityResponseType = HttpResponse<ISurveyConfiguration>;
type EntityArrayResponseType = HttpResponse<ISurveyConfiguration[]>;

@Injectable({ providedIn: 'root' })
export class SurveyConfigurationService {
  public resourceUrl = SERVER_API_URL + 'api/survey-configurations';

  constructor(protected http: HttpClient) {}

  create(surveyConfiguration: ISurveyConfiguration): Observable<EntityResponseType> {
    return this.http.post<ISurveyConfiguration>(this.resourceUrl, surveyConfiguration, { observe: 'response' });
  }

  update(surveyConfiguration: ISurveyConfiguration): Observable<EntityResponseType> {
    return this.http.put<ISurveyConfiguration>(this.resourceUrl, surveyConfiguration, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISurveyConfiguration>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISurveyConfiguration[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
