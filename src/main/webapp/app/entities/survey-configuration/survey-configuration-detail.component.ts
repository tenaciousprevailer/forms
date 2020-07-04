import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISurveyConfiguration } from 'app/shared/model/survey-configuration.model';

@Component({
  selector: 'jhi-survey-configuration-detail',
  templateUrl: './survey-configuration-detail.component.html',
})
export class SurveyConfigurationDetailComponent implements OnInit {
  surveyConfiguration: ISurveyConfiguration | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ surveyConfiguration }) => (this.surveyConfiguration = surveyConfiguration));
  }

  previousState(): void {
    window.history.back();
  }
}
