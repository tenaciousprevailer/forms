import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { FormsTestModule } from '../../../test.module';
import { SurveyStatsUpdateComponent } from 'app/entities/survey-stats/survey-stats-update.component';
import { SurveyStatsService } from 'app/entities/survey-stats/survey-stats.service';
import { SurveyStats } from 'app/shared/model/survey-stats.model';

describe('Component Tests', () => {
  describe('SurveyStats Management Update Component', () => {
    let comp: SurveyStatsUpdateComponent;
    let fixture: ComponentFixture<SurveyStatsUpdateComponent>;
    let service: SurveyStatsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FormsTestModule],
        declarations: [SurveyStatsUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SurveyStatsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SurveyStatsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SurveyStatsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SurveyStats(123);
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
        const entity = new SurveyStats();
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
