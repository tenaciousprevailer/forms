import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISurveyConfiguration } from 'app/shared/model/survey-configuration.model';
import { SurveyConfigurationService } from './survey-configuration.service';

@Component({
  templateUrl: './survey-configuration-delete-dialog.component.html',
})
export class SurveyConfigurationDeleteDialogComponent {
  surveyConfiguration?: ISurveyConfiguration;

  constructor(
    protected surveyConfigurationService: SurveyConfigurationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.surveyConfigurationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('surveyConfigurationListModification');
      this.activeModal.close();
    });
  }
}
