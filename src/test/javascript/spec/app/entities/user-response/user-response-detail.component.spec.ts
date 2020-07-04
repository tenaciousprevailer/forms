import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FormsTestModule } from '../../../test.module';
import { UserResponseDetailComponent } from 'app/entities/user-response/user-response-detail.component';
import { UserResponse } from 'app/shared/model/user-response.model';

describe('Component Tests', () => {
  describe('UserResponse Management Detail Component', () => {
    let comp: UserResponseDetailComponent;
    let fixture: ComponentFixture<UserResponseDetailComponent>;
    const route = ({ data: of({ userResponse: new UserResponse(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FormsTestModule],
        declarations: [UserResponseDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(UserResponseDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UserResponseDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load userResponse on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.userResponse).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
