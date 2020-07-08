package com.tenacious.forms.service.mapper;


import com.tenacious.forms.domain.*;
import com.tenacious.forms.service.dto.SurveyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Survey} and its DTO {@link SurveyDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SurveyMapper extends EntityMapper<SurveyDTO, Survey> {


    @Mapping(target = "questions", ignore = true)
    @Mapping(target = "removeQuestion", ignore = true)
    @Mapping(target = "answers", ignore = true)
    @Mapping(target = "removeAnswer", ignore = true)
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
