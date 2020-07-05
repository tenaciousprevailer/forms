import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ISurveyConfiguration, SurveyConfiguration } from 'app/shared/model/survey-configuration.model';
import { SurveyConfigurationService } from './survey-configuration.service';
import { ISurvey } from 'app/shared/model/survey.model';
import { SurveyService } from 'app/entities/survey/survey.service';

@Component({
  selector: 'jhi-survey-configuration-update',
  templateUrl: './survey-configuration-update.component.html',
})
export class SurveyConfigurationUpdateComponent implements OnInit {
  isSaving = false;
  surveys: ISurvey[] = [];

  editForm = this.fb.group({
    id: [],
    surveyVisibility: [],
    usersHavingVisibility: [],
    groupsHavingVisibility: [],
    resultVisibility: [],
    usersHavingResultVisibility: [],
    groupsHavingResultVisibility: [],
    maintainerUsers: [],
    maintainerUserGroups: [],
    surveyId: [],
  });

  constructor(
    protected surveyConfigurationService: SurveyConfigurationService,
    protected surveyService: SurveyService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ surveyConfiguration }) => {
      this.updateForm(surveyConfiguration);

      this.surveyService
        .query({ filter: 'surveyconfiguration-is-null' })
        .pipe(
          map((res: HttpResponse<ISurvey[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ISurvey[]) => {
          if (!surveyConfiguration.surveyId) {
            this.surveys = resBody;
          } else {
            this.surveyService
              .find(surveyConfiguration.surveyId)
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

  updateForm(surveyConfiguration: ISurveyConfiguration): void {
    this.editForm.patchValue({
      id: surveyConfiguration.id,
      surveyVisibility: surveyConfiguration.surveyVisibility,
      usersHavingVisibility: surveyConfiguration.usersHavingVisibility,
      groupsHavingVisibility: surveyConfiguration.groupsHavingVisibility,
      resultVisibility: surveyConfiguration.resultVisibility,
      usersHavingResultVisibility: surveyConfiguration.usersHavingResultVisibility,
      groupsHavingResultVisibility: surveyConfiguration.groupsHavingResultVisibility,
      maintainerUsers: surveyConfiguration.maintainerUsers,
      maintainerUserGroups: surveyConfiguration.maintainerUserGroups,
      surveyId: surveyConfiguration.surveyId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const surveyConfiguration = this.createFromForm();
    if (surveyConfiguration.id !== undefined) {
      this.subscribeToSaveResponse(this.surveyConfigurationService.update(surveyConfiguration));
    } else {
      this.subscribeToSaveResponse(this.surveyConfigurationService.create(surveyConfiguration));
    }
  }

  private createFromForm(): ISurveyConfiguration {
    return {
      ...new SurveyConfiguration(),
      id: this.editForm.get(['id'])!.value,
      surveyVisibility: this.editForm.get(['surveyVisibility'])!.value,
      usersHavingVisibility: this.editForm.get(['usersHavingVisibility'])!.value,
      groupsHavingVisibility: this.editForm.get(['groupsHavingVisibility'])!.value,
      resultVisibility: this.editForm.get(['resultVisibility'])!.value,
      usersHavingResultVisibility: this.editForm.get(['usersHavingResultVisibility'])!.value,
      groupsHavingResultVisibility: this.editForm.get(['groupsHavingResultVisibility'])!.value,
      maintainerUsers: this.editForm.get(['maintainerUsers'])!.value,
      maintainerUserGroups: this.editForm.get(['maintainerUserGroups'])!.value,
      surveyId: this.editForm.get(['surveyId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISurveyConfiguration>>): void {
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
