import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IAnswer, Answer } from 'app/shared/model/answer.model';
import { AnswerService } from './answer.service';
import { ISurvey } from 'app/shared/model/survey.model';
import { SurveyService } from 'app/entities/survey/survey.service';
import { IQuestion } from 'app/shared/model/question.model';
import { QuestionService } from 'app/entities/question/question.service';

type SelectableEntity = ISurvey | IQuestion;

@Component({
  selector: 'jhi-answer-update',
  templateUrl: './answer-update.component.html',
})
export class AnswerUpdateComponent implements OnInit {
  isSaving = false;
  surveys: ISurvey[] = [];
  questions: IQuestion[] = [];

  editForm = this.fb.group({
    id: [],
    dateCreated: [],
    takenBy: [],
    answerData: [],
    surveyId: [],
    questionId: [],
  });

  constructor(
    protected answerService: AnswerService,
    protected surveyService: SurveyService,
    protected questionService: QuestionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ answer }) => {
      if (!answer.id) {
        const today = moment().startOf('day');
        answer.dateCreated = today;
      }

      this.updateForm(answer);

      this.surveyService.query().subscribe((res: HttpResponse<ISurvey[]>) => (this.surveys = res.body || []));

      this.questionService.query().subscribe((res: HttpResponse<IQuestion[]>) => (this.questions = res.body || []));
    });
  }

  updateForm(answer: IAnswer): void {
    this.editForm.patchValue({
      id: answer.id,
      dateCreated: answer.dateCreated ? answer.dateCreated.format(DATE_TIME_FORMAT) : null,
      takenBy: answer.takenBy,
      answerData: answer.answerData,
      surveyId: answer.surveyId,
      questionId: answer.questionId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const answer = this.createFromForm();
    if (answer.id !== undefined) {
      this.subscribeToSaveResponse(this.answerService.update(answer));
    } else {
      this.subscribeToSaveResponse(this.answerService.create(answer));
    }
  }

  private createFromForm(): IAnswer {
    return {
      ...new Answer(),
      id: this.editForm.get(['id'])!.value,
      dateCreated: this.editForm.get(['dateCreated'])!.value
        ? moment(this.editForm.get(['dateCreated'])!.value, DATE_TIME_FORMAT)
        : undefined,
      takenBy: this.editForm.get(['takenBy'])!.value,
      answerData: this.editForm.get(['answerData'])!.value,
      surveyId: this.editForm.get(['surveyId'])!.value,
      questionId: this.editForm.get(['questionId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAnswer>>): void {
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
