import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FormsTestModule } from '../../../test.module';
import { SurveyConfigurationDetailComponent } from 'app/entities/survey-configuration/survey-configuration-detail.component';
import { SurveyConfiguration } from 'app/shared/model/survey-configuration.model';

describe('Component Tests', () => {
  describe('SurveyConfiguration Management Detail Component', () => {
    let comp: SurveyConfigurationDetailComponent;
    let fixture: ComponentFixture<SurveyConfigurationDetailComponent>;
    const route = ({ data: of({ surveyConfiguration: new SurveyConfiguration(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FormsTestModule],
        declarations: [SurveyConfigurationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SurveyConfigurationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SurveyConfigurationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load surveyConfiguration on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.surveyConfiguration).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
