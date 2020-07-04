import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISurveyStats } from 'app/shared/model/survey-stats.model';
import { SurveyStatsService } from './survey-stats.service';
import { SurveyStatsDeleteDialogComponent } from './survey-stats-delete-dialog.component';

@Component({
  selector: 'jhi-survey-stats',
  templateUrl: './survey-stats.component.html',
})
export class SurveyStatsComponent implements OnInit, OnDestroy {
  surveyStats?: ISurveyStats[];
  eventSubscriber?: Subscription;

  constructor(
    protected surveyStatsService: SurveyStatsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.surveyStatsService.query().subscribe((res: HttpResponse<ISurveyStats[]>) => (this.surveyStats = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSurveyStats();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISurveyStats): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSurveyStats(): void {
    this.eventSubscriber = this.eventManager.subscribe('surveyStatsListModification', () => this.loadAll());
  }

  delete(surveyStats: ISurveyStats): void {
    const modalRef = this.modalService.open(SurveyStatsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.surveyStats = surveyStats;
  }
}
