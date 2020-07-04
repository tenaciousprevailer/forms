import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISurveyConfiguration, SurveyConfiguration } from 'app/shared/model/survey-configuration.model';
import { SurveyConfigurationService } from './survey-configuration.service';
import { SurveyConfigurationComponent } from './survey-configuration.component';
import { SurveyConfigurationDetailComponent } from './survey-configuration-detail.component';
import { SurveyConfigurationUpdateComponent } from './survey-configuration-update.component';

@Injectable({ providedIn: 'root' })
export class SurveyConfigurationResolve implements Resolve<ISurveyConfiguration> {
  constructor(private service: SurveyConfigurationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISurveyConfiguration> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((surveyConfiguration: HttpResponse<SurveyConfiguration>) => {
          if (surveyConfiguration.body) {
            return of(surveyConfiguration.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SurveyConfiguration());
  }
}

export const surveyConfigurationRoute: Routes = [
  {
    path: '',
    component: SurveyConfigurationComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'formsApp.surveyConfiguration.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SurveyConfigurationDetailComponent,
    resolve: {
      surveyConfiguration: SurveyConfigurationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'formsApp.surveyConfiguration.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SurveyConfigurationUpdateComponent,
    resolve: {
      surveyConfiguration: SurveyConfigurationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'formsApp.surveyConfiguration.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SurveyConfigurationUpdateComponent,
    resolve: {
      surveyConfiguration: SurveyConfigurationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'formsApp.surveyConfiguration.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
