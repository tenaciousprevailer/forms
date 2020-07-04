import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { FormsTestModule } from '../../../test.module';
import { SurveyConfigurationComponent } from 'app/entities/survey-configuration/survey-configuration.component';
import { SurveyConfigurationService } from 'app/entities/survey-configuration/survey-configuration.service';
import { SurveyConfiguration } from 'app/shared/model/survey-configuration.model';

describe('Component Tests', () => {
  describe('SurveyConfiguration Management Component', () => {
    let comp: SurveyConfigurationComponent;
    let fixture: ComponentFixture<SurveyConfigurationComponent>;
    let service: SurveyConfigurationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FormsTestModule],
        declarations: [SurveyConfigurationComponent],
      })
        .overrideTemplate(SurveyConfigurationComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SurveyConfigurationComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SurveyConfigurationService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SurveyConfiguration(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.surveyConfigurations && comp.surveyConfigurations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
