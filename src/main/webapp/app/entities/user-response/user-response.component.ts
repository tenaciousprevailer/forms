import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IUserResponse } from 'app/shared/model/user-response.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { UserResponseService } from './user-response.service';
import { UserResponseDeleteDialogComponent } from './user-response-delete-dialog.component';

@Component({
  selector: 'jhi-user-response',
  templateUrl: './user-response.component.html',
})
export class UserResponseComponent implements OnInit, OnDestroy {
  userResponses: IUserResponse[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected userResponseService: UserResponseService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.userResponses = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.userResponseService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<IUserResponse[]>) => this.paginateUserResponses(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.userResponses = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInUserResponses();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IUserResponse): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInUserResponses(): void {
    this.eventSubscriber = this.eventManager.subscribe('userResponseListModification', () => this.reset());
  }

  delete(userResponse: IUserResponse): void {
    const modalRef = this.modalService.open(UserResponseDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.userResponse = userResponse;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateUserResponses(data: IUserResponse[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.userResponses.push(data[i]);
      }
    }
  }
}
