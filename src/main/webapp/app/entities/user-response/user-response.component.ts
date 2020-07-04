import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IUserResponse } from 'app/shared/model/user-response.model';
import { UserResponseService } from './user-response.service';
import { UserResponseDeleteDialogComponent } from './user-response-delete-dialog.component';

@Component({
  selector: 'jhi-user-response',
  templateUrl: './user-response.component.html',
})
export class UserResponseComponent implements OnInit, OnDestroy {
  userResponses?: IUserResponse[];
  eventSubscriber?: Subscription;

  constructor(
    protected userResponseService: UserResponseService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.userResponseService.query().subscribe((res: HttpResponse<IUserResponse[]>) => (this.userResponses = res.body || []));
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
    this.eventSubscriber = this.eventManager.subscribe('userResponseListModification', () => this.loadAll());
  }

  delete(userResponse: IUserResponse): void {
    const modalRef = this.modalService.open(UserResponseDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.userResponse = userResponse;
  }
}
