import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'survey',
        loadChildren: () => import('./survey/survey.module').then(m => m.FormsSurveyModule),
      },
      {
        path: 'survey-configuration',
        loadChildren: () => import('./survey-configuration/survey-configuration.module').then(m => m.FormsSurveyConfigurationModule),
      },
      {
        path: 'survey-stats',
        loadChildren: () => import('./survey-stats/survey-stats.module').then(m => m.FormsSurveyStatsModule),
      },
      {
        path: 'question',
        loadChildren: () => import('./question/question.module').then(m => m.FormsQuestionModule),
      },
      {
        path: 'user-response',
        loadChildren: () => import('./user-response/user-response.module').then(m => m.FormsUserResponseModule),
      },
      {
        path: 'answer',
        loadChildren: () => import('./answer/answer.module').then(m => m.FormsAnswerModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class FormsEntityModule {}
