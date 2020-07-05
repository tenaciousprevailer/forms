package com.tenacious.forms.service.mapper;


import com.tenacious.forms.domain.*;
import com.tenacious.forms.service.dto.SurveyConfigurationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SurveyConfiguration} and its DTO {@link SurveyConfigurationDTO}.
 */
@Mapper(componentModel = "spring", uses = {SurveyMapper.class})
public interface SurveyConfigurationMapper extends EntityMapper<SurveyConfigurationDTO, SurveyConfiguration> {

    @Mapping(source = "survey.id", target = "surveyId")
    SurveyConfigurationDTO toDto(SurveyConfiguration surveyConfiguration);

    @Mapping(source = "surveyId", target = "survey")
    SurveyConfiguration toEntity(SurveyConfigurationDTO surveyConfigurationDTO);

    default SurveyConfiguration fromId(Long id) {
        if (id == null) {
            return null;
        }
        SurveyConfiguration surveyConfiguration = new SurveyConfiguration();
        surveyConfiguration.setId(id);
        return surveyConfiguration;
    }
}
