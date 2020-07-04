import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FormsTestModule } from '../../../test.module';
import { SurveyStatsDetailComponent } from 'app/entities/survey-stats/survey-stats-detail.component';
import { SurveyStats } from 'app/shared/model/survey-stats.model';

describe('Component Tests', () => {
  describe('SurveyStats Management Detail Component', () => {
    let comp: SurveyStatsDetailComponent;
    let fixture: ComponentFixture<SurveyStatsDetailComponent>;
    const route = ({ data: of({ surveyStats: new SurveyStats(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FormsTestModule],
        declarations: [SurveyStatsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SurveyStatsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SurveyStatsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load surveyStats on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.surveyStats).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
