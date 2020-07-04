import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { SurveyConfigurationService } from 'app/entities/survey-configuration/survey-configuration.service';
import { ISurveyConfiguration, SurveyConfiguration } from 'app/shared/model/survey-configuration.model';
import { VisibilityType } from 'app/shared/model/enumerations/visibility-type.model';

describe('Service Tests', () => {
  describe('SurveyConfiguration Service', () => {
    let injector: TestBed;
    let service: SurveyConfigurationService;
    let httpMock: HttpTestingController;
    let elemDefault: ISurveyConfiguration;
    let expectedResult: ISurveyConfiguration | ISurveyConfiguration[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(SurveyConfigurationService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new SurveyConfiguration(
        0,
        VisibilityType.ANYONE_WITH_LINK,
        'AAAAAAA',
        'AAAAAAA',
        VisibilityType.ANYONE_WITH_LINK,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a SurveyConfiguration', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new SurveyConfiguration()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a SurveyConfiguration', () => {
        const returnedFromService = Object.assign(
          {
            surveyVisibility: 'BBBBBB',
            usersHavingVisibility: 'BBBBBB',
            groupsHavingVisibility: 'BBBBBB',
            resultVisibility: 'BBBBBB',
            usersHavingResultVisibility: 'BBBBBB',
            groupsHavingResultVisibility: 'BBBBBB',
            maintainerUsers: 'BBBBBB',
            maintainerUserGroups: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of SurveyConfiguration', () => {
        const returnedFromService = Object.assign(
          {
            surveyVisibility: 'BBBBBB',
            usersHavingVisibility: 'BBBBBB',
            groupsHavingVisibility: 'BBBBBB',
            resultVisibility: 'BBBBBB',
            usersHavingResultVisibility: 'BBBBBB',
            groupsHavingResultVisibility: 'BBBBBB',
            maintainerUsers: 'BBBBBB',
            maintainerUserGroups: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a SurveyConfiguration', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
