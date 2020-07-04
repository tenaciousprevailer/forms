import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { FormsTestModule } from '../../../test.module';
import { SurveyConfigurationUpdateComponent } from 'app/entities/survey-configuration/survey-configuration-update.component';
import { SurveyConfigurationService } from 'app/entities/survey-configuration/survey-configuration.service';
import { SurveyConfiguration } from 'app/shared/model/survey-configuration.model';

describe('Component Tests', () => {
  describe('SurveyConfiguration Management Update Component', () => {
    let comp: SurveyConfigurationUpdateComponent;
    let fixture: ComponentFixture<SurveyConfigurationUpdateComponent>;
    let service: SurveyConfigurationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FormsTestModule],
        declarations: [SurveyConfigurationUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SurveyConfigurationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SurveyConfigurationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SurveyConfigurationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SurveyConfiguration(123);
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
        const entity = new SurveyConfiguration();
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
