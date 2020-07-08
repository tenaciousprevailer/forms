import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IQuestion, Question } from 'app/shared/model/question.model';
import { QuestionService } from './question.service';
import { ISurvey } from 'app/shared/model/survey.model';
import { SurveyService } from 'app/entities/survey/survey.service';

@Component({
  selector: 'jhi-question-update',
  templateUrl: './question-update.component.html',
})
export class QuestionUpdateComponent implements OnInit {
  isSaving = false;
  surveys: ISurvey[] = [];

  editForm = this.fb.group({
    id: [],
    text: [],
    type: [],
    jsonData: [],
    totalResponseCount: [],
    surveyId: [],
  });

  constructor(
    protected questionService: QuestionService,
    protected surveyService: SurveyService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ question }) => {
      this.updateForm(question);

      this.surveyService.query().subscribe((res: HttpResponse<ISurvey[]>) => (this.surveys = res.body || []));
    });
  }

  updateForm(question: IQuestion): void {
    this.editForm.patchValue({
      id: question.id,
      text: question.text,
      type: question.type,
      jsonData: question.jsonData,
      totalResponseCount: question.totalResponseCount,
      surveyId: question.surveyId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const question = this.createFromForm();
    if (question.id !== undefined) {
      this.subscribeToSaveResponse(this.questionService.update(question));
    } else {
      this.subscribeToSaveResponse(this.questionService.create(question));
    }
  }

  private createFromForm(): IQuestion {
    return {
      ...new Question(),
      id: this.editForm.get(['id'])!.value,
      text: this.editForm.get(['text'])!.value,
      type: this.editForm.get(['type'])!.value,
      jsonData: this.editForm.get(['jsonData'])!.value,
      totalResponseCount: this.editForm.get(['totalResponseCount'])!.value,
      surveyId: this.editForm.get(['surveyId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IQuestion>>): void {
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
