import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISurveyStats } from 'app/shared/model/survey-stats.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { SurveyStatsService } from './survey-stats.service';
import { SurveyStatsDeleteDialogComponent } from './survey-stats-delete-dialog.component';

@Component({
  selector: 'jhi-survey-stats',
  templateUrl: './survey-stats.component.html',
})
export class SurveyStatsComponent implements OnInit, OnDestroy {
  surveyStats: ISurveyStats[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected surveyStatsService: SurveyStatsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.surveyStats = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.surveyStatsService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<ISurveyStats[]>) => this.paginateSurveyStats(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.surveyStats = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
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
    this.eventSubscriber = this.eventManager.subscribe('surveyStatsListModification', () => this.reset());
  }

  delete(surveyStats: ISurveyStats): void {
    const modalRef = this.modalService.open(SurveyStatsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.surveyStats = surveyStats;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateSurveyStats(data: ISurveyStats[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.surveyStats.push(data[i]);
      }
    }
  }
}
