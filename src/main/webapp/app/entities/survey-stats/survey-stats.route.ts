import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISurveyStats, SurveyStats } from 'app/shared/model/survey-stats.model';
import { SurveyStatsService } from './survey-stats.service';
import { SurveyStatsComponent } from './survey-stats.component';
import { SurveyStatsDetailComponent } from './survey-stats-detail.component';
import { SurveyStatsUpdateComponent } from './survey-stats-update.component';

@Injectable({ providedIn: 'root' })
export class SurveyStatsResolve implements Resolve<ISurveyStats> {
  constructor(private service: SurveyStatsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISurveyStats> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((surveyStats: HttpResponse<SurveyStats>) => {
          if (surveyStats.body) {
            return of(surveyStats.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SurveyStats());
  }
}

export const surveyStatsRoute: Routes = [
  {
    path: '',
    component: SurveyStatsComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'formsApp.surveyStats.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SurveyStatsDetailComponent,
    resolve: {
      surveyStats: SurveyStatsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'formsApp.surveyStats.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SurveyStatsUpdateComponent,
    resolve: {
      surveyStats: SurveyStatsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'formsApp.surveyStats.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SurveyStatsUpdateComponent,
    resolve: {
      surveyStats: SurveyStatsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'formsApp.surveyStats.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
