import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISurveyStats } from 'app/shared/model/survey-stats.model';
import { SurveyStatsService } from './survey-stats.service';

@Component({
  templateUrl: './survey-stats-delete-dialog.component.html',
})
export class SurveyStatsDeleteDialogComponent {
  surveyStats?: ISurveyStats;

  constructor(
    protected surveyStatsService: SurveyStatsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.surveyStatsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('surveyStatsListModification');
      this.activeModal.close();
    });
  }
}
