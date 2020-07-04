import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { FormsTestModule } from '../../../test.module';
import { SurveyStatsComponent } from 'app/entities/survey-stats/survey-stats.component';
import { SurveyStatsService } from 'app/entities/survey-stats/survey-stats.service';
import { SurveyStats } from 'app/shared/model/survey-stats.model';

describe('Component Tests', () => {
  describe('SurveyStats Management Component', () => {
    let comp: SurveyStatsComponent;
    let fixture: ComponentFixture<SurveyStatsComponent>;
    let service: SurveyStatsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FormsTestModule],
        declarations: [SurveyStatsComponent],
      })
        .overrideTemplate(SurveyStatsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SurveyStatsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SurveyStatsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SurveyStats(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.surveyStats && comp.surveyStats[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
