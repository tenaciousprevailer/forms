package com.tenacious.forms.service.mapper;


import com.tenacious.forms.domain.*;
import com.tenacious.forms.service.dto.SurveyConfigurationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SurveyConfiguration} and its DTO {@link SurveyConfigurationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SurveyConfigurationMapper extends EntityMapper<SurveyConfigurationDTO, SurveyConfiguration> {


    @Mapping(target = "survey", ignore = true)
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
