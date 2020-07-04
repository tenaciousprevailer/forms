import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FormsSharedModule } from 'app/shared/shared.module';
import { SurveyStatsComponent } from './survey-stats.component';
import { SurveyStatsDetailComponent } from './survey-stats-detail.component';
import { SurveyStatsUpdateComponent } from './survey-stats-update.component';
import { SurveyStatsDeleteDialogComponent } from './survey-stats-delete-dialog.component';
import { surveyStatsRoute } from './survey-stats.route';

@NgModule({
  imports: [FormsSharedModule, RouterModule.forChild(surveyStatsRoute)],
  declarations: [SurveyStatsComponent, SurveyStatsDetailComponent, SurveyStatsUpdateComponent, SurveyStatsDeleteDialogComponent],
  entryComponents: [SurveyStatsDeleteDialogComponent],
})
export class FormsSurveyStatsModule {}
