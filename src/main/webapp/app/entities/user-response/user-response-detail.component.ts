import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUserResponse } from 'app/shared/model/user-response.model';

@Component({
  selector: 'jhi-user-response-detail',
  templateUrl: './user-response-detail.component.html',
})
export class UserResponseDetailComponent implements OnInit {
  userResponse: IUserResponse | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userResponse }) => (this.userResponse = userResponse));
  }

  previousState(): void {
    window.history.back();
  }
}
