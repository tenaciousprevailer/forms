package com.tenacious.forms.service.mapper;


import com.tenacious.forms.domain.*;
import com.tenacious.forms.service.dto.SurveyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Survey} and its DTO {@link SurveyDTO}.
 */
@Mapper(componentModel = "spring", uses = {SurveyConfigurationMapper.class, SurveyStatsMapper.class})
public interface SurveyMapper extends EntityMapper<SurveyDTO, Survey> {

    @Mapping(source = "surveyConfiguration.id", target = "surveyConfigurationId")
    @Mapping(source = "surveyStats.id", target = "surveyStatsId")
    SurveyDTO toDto(Survey survey);

    @Mapping(source = "surveyConfigurationId", target = "surveyConfiguration")
    @Mapping(source = "surveyStatsId", target = "surveyStats")
    @Mapping(target = "questions", ignore = true)
    @Mapping(target = "removeQuestion", ignore = true)
    @Mapping(target = "userResponses", ignore = true)
    @Mapping(target = "removeUserResponse", ignore = true)
    Survey toEntity(SurveyDTO surveyDTO);

    default Survey fromId(Long id) {
        if (id == null) {
            return null;
        }
        Survey survey = new Survey();
        survey.setId(id);
        return survey;
    }
}
