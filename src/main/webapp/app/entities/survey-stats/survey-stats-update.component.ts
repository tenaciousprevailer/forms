import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISurveyStats, SurveyStats } from 'app/shared/model/survey-stats.model';
import { SurveyStatsService } from './survey-stats.service';

@Component({
  selector: 'jhi-survey-stats-update',
  templateUrl: './survey-stats-update.component.html',
})
export class SurveyStatsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    totalResponseCount: [],
  });

  constructor(protected surveyStatsService: SurveyStatsService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ surveyStats }) => {
      this.updateForm(surveyStats);
    });
  }

  updateForm(surveyStats: ISurveyStats): void {
    this.editForm.patchValue({
      id: surveyStats.id,
      totalResponseCount: surveyStats.totalResponseCount,
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
}
