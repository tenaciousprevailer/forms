import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISurvey, Survey } from 'app/shared/model/survey.model';
import { SurveyService } from './survey.service';
import { ISurveyConfiguration } from 'app/shared/model/survey-configuration.model';
import { SurveyConfigurationService } from 'app/entities/survey-configuration/survey-configuration.service';
import { ISurveyStats } from 'app/shared/model/survey-stats.model';
import { SurveyStatsService } from 'app/entities/survey-stats/survey-stats.service';

type SelectableEntity = ISurveyConfiguration | ISurveyStats;

@Component({
  selector: 'jhi-survey-update',
  templateUrl: './survey-update.component.html',
})
export class SurveyUpdateComponent implements OnInit {
  isSaving = false;
  surveyconfigurations: ISurveyConfiguration[] = [];
  surveystats: ISurveyStats[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    description: [],
    dateStart: [],
    dateEnd: [],
    dateCreated: [],
    dateLastUpdated: [],
    createdBy: [],
    lastUpdatedBy: [],
    status: [],
    surveyConfigurationId: [],
    surveyStatsId: [],
  });

  constructor(
    protected surveyService: SurveyService,
    protected surveyConfigurationService: SurveyConfigurationService,
    protected surveyStatsService: SurveyStatsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ survey }) => {
      if (!survey.id) {
        const today = moment().startOf('day');
        survey.dateStart = today;
        survey.dateEnd = today;
        survey.dateCreated = today;
        survey.dateLastUpdated = today;
      }

      this.updateForm(survey);

      this.surveyConfigurationService
        .query({ filter: 'survey-is-null' })
        .pipe(
          map((res: HttpResponse<ISurveyConfiguration[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ISurveyConfiguration[]) => {
          if (!survey.surveyConfigurationId) {
            this.surveyconfigurations = resBody;
          } else {
            this.surveyConfigurationService
              .find(survey.surveyConfigurationId)
              .pipe(
                map((subRes: HttpResponse<ISurveyConfiguration>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ISurveyConfiguration[]) => (this.surveyconfigurations = concatRes));
          }
        });

      this.surveyStatsService
        .query({ filter: 'survey-is-null' })
        .pipe(
          map((res: HttpResponse<ISurveyStats[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ISurveyStats[]) => {
          if (!survey.surveyStatsId) {
            this.surveystats = resBody;
          } else {
            this.surveyStatsService
              .find(survey.surveyStatsId)
              .pipe(
                map((subRes: HttpResponse<ISurveyStats>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ISurveyStats[]) => (this.surveystats = concatRes));
          }
        });
    });
  }

  updateForm(survey: ISurvey): void {
    this.editForm.patchValue({
      id: survey.id,
      name: survey.name,
      description: survey.description,
      dateStart: survey.dateStart ? survey.dateStart.format(DATE_TIME_FORMAT) : null,
      dateEnd: survey.dateEnd ? survey.dateEnd.format(DATE_TIME_FORMAT) : null,
      dateCreated: survey.dateCreated ? survey.dateCreated.format(DATE_TIME_FORMAT) : null,
      dateLastUpdated: survey.dateLastUpdated ? survey.dateLastUpdated.format(DATE_TIME_FORMAT) : null,
      createdBy: survey.createdBy,
      lastUpdatedBy: survey.lastUpdatedBy,
      status: survey.status,
      surveyConfigurationId: survey.surveyConfigurationId,
      surveyStatsId: survey.surveyStatsId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const survey = this.createFromForm();
    if (survey.id !== undefined) {
      this.subscribeToSaveResponse(this.surveyService.update(survey));
    } else {
      this.subscribeToSaveResponse(this.surveyService.create(survey));
    }
  }

  private createFromForm(): ISurvey {
    return {
      ...new Survey(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      description: this.editForm.get(['description'])!.value,
      dateStart: this.editForm.get(['dateStart'])!.value ? moment(this.editForm.get(['dateStart'])!.value, DATE_TIME_FORMAT) : undefined,
      dateEnd: this.editForm.get(['dateEnd'])!.value ? moment(this.editForm.get(['dateEnd'])!.value, DATE_TIME_FORMAT) : undefined,
      dateCreated: this.editForm.get(['dateCreated'])!.value
        ? moment(this.editForm.get(['dateCreated'])!.value, DATE_TIME_FORMAT)
        : undefined,
      dateLastUpdated: this.editForm.get(['dateLastUpdated'])!.value
        ? moment(this.editForm.get(['dateLastUpdated'])!.value, DATE_TIME_FORMAT)
        : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      lastUpdatedBy: this.editForm.get(['lastUpdatedBy'])!.value,
      status: this.editForm.get(['status'])!.value,
      surveyConfigurationId: this.editForm.get(['surveyConfigurationId'])!.value,
      surveyStatsId: this.editForm.get(['surveyStatsId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISurvey>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
