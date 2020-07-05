import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ISurveyStats, SurveyStats } from 'app/shared/model/survey-stats.model';
import { SurveyStatsService } from './survey-stats.service';
import { ISurvey } from 'app/shared/model/survey.model';
import { SurveyService } from 'app/entities/survey/survey.service';

@Component({
  selector: 'jhi-survey-stats-update',
  templateUrl: './survey-stats-update.component.html',
})
export class SurveyStatsUpdateComponent implements OnInit {
  isSaving = false;
  surveys: ISurvey[] = [];

  editForm = this.fb.group({
    id: [],
    totalResponseCount: [],
    surveyId: [],
  });

  constructor(
    protected surveyStatsService: SurveyStatsService,
    protected surveyService: SurveyService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ surveyStats }) => {
      this.updateForm(surveyStats);

      this.surveyService
        .query({ filter: 'surveystats-is-null' })
        .pipe(
          map((res: HttpResponse<ISurvey[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ISurvey[]) => {
          if (!surveyStats.surveyId) {
            this.surveys = resBody;
          } else {
            this.surveyService
              .find(surveyStats.surveyId)
              .pipe(
                map((subRes: HttpResponse<ISurvey>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ISurvey[]) => (this.surveys = concatRes));
          }
        });
    });
  }

  updateForm(surveyStats: ISurveyStats): void {
    this.editForm.patchValue({
      id: surveyStats.id,
      totalResponseCount: surveyStats.totalResponseCount,
      surveyId: surveyStats.surveyId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const surveyStats = this.createFromForm();
    if (surveyStats.id !== undefined) {
      this.subscribeToSaveResponse(this.surveyStatsService.update(surveyStats));
    } else {
      this.subscribeToSaveResponse(this.surveyStatsService.create(surveyStats));
    }
  }

  private createFromForm(): ISurveyStats {
    return {
      ...new SurveyStats(),
      id: this.editForm.get(['id'])!.value,
      totalResponseCount: this.editForm.get(['totalResponseCount'])!.value,
      surveyId: this.editForm.get(['surveyId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISurveyStats>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: ISurvey): any {
    return item.id;
  }
}
