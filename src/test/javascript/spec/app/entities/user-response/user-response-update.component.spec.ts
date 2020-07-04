import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { FormsTestModule } from '../../../test.module';
import { UserResponseUpdateComponent } from 'app/entities/user-response/user-response-update.component';
import { UserResponseService } from 'app/entities/user-response/user-response.service';
import { UserResponse } from 'app/shared/model/user-response.model';

describe('Component Tests', () => {
  describe('UserResponse Management Update Component', () => {
    let comp: UserResponseUpdateComponent;
    let fixture: ComponentFixture<UserResponseUpdateComponent>;
    let service: UserResponseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FormsTestModule],
        declarations: [UserResponseUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(UserResponseUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserResponseUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserResponseService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new UserResponse(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new UserResponse();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
