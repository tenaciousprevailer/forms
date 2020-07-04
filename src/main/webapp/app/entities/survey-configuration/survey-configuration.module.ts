import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FormsSharedModule } from 'app/shared/shared.module';
import { SurveyConfigurationComponent } from './survey-configuration.component';
import { SurveyConfigurationDetailComponent } from './survey-configuration-detail.component';
import { SurveyConfigurationUpdateComponent } from './survey-configuration-update.component';
import { SurveyConfigurationDeleteDialogComponent } from './survey-configuration-delete-dialog.component';
import { surveyConfigurationRoute } from './survey-configuration.route';

@NgModule({
  imports: [FormsSharedModule, RouterModule.forChild(surveyConfigurationRoute)],
  declarations: [
    SurveyConfigurationComponent,
    SurveyConfigurationDetailComponent,
    SurveyConfigurationUpdateComponent,
    SurveyConfigurationDeleteDialogComponent,
  ],
  entryComponents: [SurveyConfigurationDeleteDialogComponent],
})
export class FormsSurveyConfigurationModule {}
