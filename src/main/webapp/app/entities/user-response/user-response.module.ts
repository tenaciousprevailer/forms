import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FormsSharedModule } from 'app/shared/shared.module';
import { UserResponseComponent } from './user-response.component';
import { UserResponseDetailComponent } from './user-response-detail.component';
import { UserResponseUpdateComponent } from './user-response-update.component';
import { UserResponseDeleteDialogComponent } from './user-response-delete-dialog.component';
import { userResponseRoute } from './user-response.route';

@NgModule({
  imports: [FormsSharedModule, RouterModule.forChild(userResponseRoute)],
  declarations: [UserResponseComponent, UserResponseDetailComponent, UserResponseUpdateComponent, UserResponseDeleteDialogComponent],
  entryComponents: [UserResponseDeleteDialogComponent],
})
export class FormsUserResponseModule {}
