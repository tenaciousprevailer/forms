import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUserResponse } from 'app/shared/model/user-response.model';
import { UserResponseService } from './user-response.service';

@Component({
  templateUrl: './user-response-delete-dialog.component.html',
})
export class UserResponseDeleteDialogComponent {
  userResponse?: IUserResponse;

  constructor(
    protected userResponseService: UserResponseService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.userResponseService.delete(id).subscribe(() => {
      this.eventManager.broadcast('userResponseListModification');
      this.activeModal.close();
    });
  }
}
