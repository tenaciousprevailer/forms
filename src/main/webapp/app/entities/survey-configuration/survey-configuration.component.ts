import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISurveyConfiguration } from 'app/shared/model/survey-configuration.model';
import { SurveyConfigurationService } from './survey-configuration.service';
import { SurveyConfigurationDeleteDialogComponent } from './survey-configuration-delete-dialog.component';

@Component({
  selector: 'jhi-survey-configuration',
  templateUrl: './survey-configuration.component.html',
})
export class SurveyConfigurationComponent implements OnInit, OnDestroy {
  surveyConfigurations?: ISurveyConfiguration[];
  eventSubscriber?: Subscription;

  constructor(
    protected surveyConfigurationService: SurveyConfigurationService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.surveyConfigurationService
      .query()
      .subscribe((res: HttpResponse<ISurveyConfiguration[]>) => (this.surveyConfigurations = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSurveyConfigurations();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISurveyConfiguration): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSurveyConfigurations(): void {
    this.eventSubscriber = this.eventManager.subscribe('surveyConfigurationListModification', () => this.loadAll());
  }

  delete(surveyConfiguration: ISurveyConfiguration): void {
    const modalRef = this.modalService.open(SurveyConfigurationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.surveyConfiguration = surveyConfiguration;
  }
}
