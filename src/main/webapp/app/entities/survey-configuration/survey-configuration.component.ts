import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISurveyConfiguration } from 'app/shared/model/survey-configuration.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { SurveyConfigurationService } from './survey-configuration.service';
import { SurveyConfigurationDeleteDialogComponent } from './survey-configuration-delete-dialog.component';

@Component({
  selector: 'jhi-survey-configuration',
  templateUrl: './survey-configuration.component.html',
})
export class SurveyConfigurationComponent implements OnInit, OnDestroy {
  surveyConfigurations: ISurveyConfiguration[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected surveyConfigurationService: SurveyConfigurationService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.surveyConfigurations = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.surveyConfigurationService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<ISurveyConfiguration[]>) => this.paginateSurveyConfigurations(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.surveyConfigurations = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
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
    this.eventSubscriber = this.eventManager.subscribe('surveyConfigurationListModification', () => this.reset());
  }

  delete(surveyConfiguration: ISurveyConfiguration): void {
    const modalRef = this.modalService.open(SurveyConfigurationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.surveyConfiguration = surveyConfiguration;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateSurveyConfigurations(data: ISurveyConfiguration[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.surveyConfigurations.push(data[i]);
      }
    }
  }
}
