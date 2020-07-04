import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IUserResponse, UserResponse } from 'app/shared/model/user-response.model';
import { UserResponseService } from './user-response.service';
import { ISurvey } from 'app/shared/model/survey.model';
import { SurveyService } from 'app/entities/survey/survey.service';
import { IQuestion } from 'app/shared/model/question.model';
import { QuestionService } from 'app/entities/question/question.service';

type SelectableEntity = ISurvey | IQuestion;

@Component({
  selector: 'jhi-user-response-update',
  templateUrl: './user-response-update.component.html',
})
export class UserResponseUpdateComponent implements OnInit {
  isSaving = false;
  surveys: ISurvey[] = [];
  questions: IQuestion[] = [];

  editForm = this.fb.group({
    id: [],
    dateCreated: [],
    takenBy: [],
    responseData: [],
    surveyId: [],
    questionId: [],
  });

  constructor(
    protected userResponseService: UserResponseService,
    protected surveyService: SurveyService,
    protected questionService: QuestionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userResponse }) => {
      if (!userResponse.id) {
        const today = moment().startOf('day');
        userResponse.dateCreated = today;
      }

      this.updateForm(userResponse);

      this.surveyService.query().subscribe((res: HttpResponse<ISurvey[]>) => (this.surveys = res.body || []));

      this.questionService.query().subscribe((res: HttpResponse<IQuestion[]>) => (this.questions = res.body || []));
    });
  }

  updateForm(userResponse: IUserResponse): void {
    this.editForm.patchValue({
      id: userResponse.id,
      dateCreated: userResponse.dateCreated ? userResponse.dateCreated.format(DATE_TIME_FORMAT) : null,
      takenBy: userResponse.takenBy,
      responseData: userResponse.responseData,
      surveyId: userResponse.surveyId,
      questionId: userResponse.questionId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const userResponse = this.createFromForm();
    if (userResponse.id !== undefined) {
      this.subscribeToSaveResponse(this.userResponseService.update(userResponse));
    } else {
      this.subscribeToSaveResponse(this.userResponseService.create(userResponse));
    }
  }

  private createFromForm(): IUserResponse {
    return {
      ...new UserResponse(),
      id: this.editForm.get(['id'])!.value,
      dateCreated: this.editForm.get(['dateCreated'])!.value
        ? moment(this.editForm.get(['dateCreated'])!.value, DATE_TIME_FORMAT)
        : undefined,
      takenBy: this.editForm.get(['takenBy'])!.value,
      responseData: this.editForm.get(['responseData'])!.value,
      surveyId: this.editForm.get(['surveyId'])!.value,
      questionId: this.editForm.get(['questionId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserResponse>>): void {
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
