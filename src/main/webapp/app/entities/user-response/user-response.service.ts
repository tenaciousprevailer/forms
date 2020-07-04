import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IUserResponse } from 'app/shared/model/user-response.model';

type EntityResponseType = HttpResponse<IUserResponse>;
type EntityArrayResponseType = HttpResponse<IUserResponse[]>;

@Injectable({ providedIn: 'root' })
export class UserResponseService {
  public resourceUrl = SERVER_API_URL + 'api/user-responses';

  constructor(protected http: HttpClient) {}

  create(userResponse: IUserResponse): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(userResponse);
    return this.http
      .post<IUserResponse>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(userResponse: IUserResponse): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(userResponse);
    return this.http
      .put<IUserResponse>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IUserResponse>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IUserResponse[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(userResponse: IUserResponse): IUserResponse {
    const copy: IUserResponse = Object.assign({}, userResponse, {
      dateCreated: userResponse.dateCreated && userResponse.dateCreated.isValid() ? userResponse.dateCreated.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateCreated = res.body.dateCreated ? moment(res.body.dateCreated) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((userResponse: IUserResponse) => {
        userResponse.dateCreated = userResponse.dateCreated ? moment(userResponse.dateCreated) : undefined;
      });
    }
    return res;
  }
}
