import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FormsSharedModule } from 'app/shared/shared.module';
import { SurveyComponent } from './survey.component';
import { SurveyDetailComponent } from './survey-detail.component';
import { SurveyUpdateComponent } from './survey-update.component';
import { SurveyDeleteDialogComponent } from './survey-delete-dialog.component';
import { surveyRoute } from './survey.route';

@NgModule({
  imports: [FormsSharedModule, RouterModule.forChild(surveyRoute)],
  declarations: [SurveyComponent, SurveyDetailComponent, SurveyUpdateComponent, SurveyDeleteDialogComponent],
  entryComponents: [SurveyDeleteDialogComponent],
})
export class FormsSurveyModule {}
