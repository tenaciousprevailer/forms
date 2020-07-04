import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IUserResponse, UserResponse } from 'app/shared/model/user-response.model';
import { UserResponseService } from './user-response.service';
import { UserResponseComponent } from './user-response.component';
import { UserResponseDetailComponent } from './user-response-detail.component';
import { UserResponseUpdateComponent } from './user-response-update.component';

@Injectable({ providedIn: 'root' })
export class UserResponseResolve implements Resolve<IUserResponse> {
  constructor(private service: UserResponseService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUserResponse> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((userResponse: HttpResponse<UserResponse>) => {
          if (userResponse.body) {
            return of(userResponse.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new UserResponse());
  }
}

export const userResponseRoute: Routes = [
  {
    path: '',
    component: UserResponseComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'formsApp.userResponse.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: UserResponseDetailComponent,
    resolve: {
      userResponse: UserResponseResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'formsApp.userResponse.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: UserResponseUpdateComponent,
    resolve: {
      userResponse: UserResponseResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'formsApp.userResponse.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: UserResponseUpdateComponent,
    resolve: {
      userResponse: UserResponseResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'formsApp.userResponse.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
