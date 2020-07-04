import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISurveyStats } from 'app/shared/model/survey-stats.model';

@Component({
  selector: 'jhi-survey-stats-detail',
  templateUrl: './survey-stats-detail.component.html',
})
export class SurveyStatsDetailComponent implements OnInit {
  surveyStats: ISurveyStats | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ surveyStats }) => (this.surveyStats = surveyStats));
  }

  previousState(): void {
    window.history.back();
  }
}
