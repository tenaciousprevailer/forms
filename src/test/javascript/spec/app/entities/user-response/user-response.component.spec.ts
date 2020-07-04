import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { FormsTestModule } from '../../../test.module';
import { UserResponseComponent } from 'app/entities/user-response/user-response.component';
import { UserResponseService } from 'app/entities/user-response/user-response.service';
import { UserResponse } from 'app/shared/model/user-response.model';

describe('Component Tests', () => {
  describe('UserResponse Management Component', () => {
    let comp: UserResponseComponent;
    let fixture: ComponentFixture<UserResponseComponent>;
    let service: UserResponseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FormsTestModule],
        declarations: [UserResponseComponent],
      })
        .overrideTemplate(UserResponseComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserResponseComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserResponseService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new UserResponse(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.userResponses && comp.userResponses[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
