package com.tenacious.forms.service.mapper;


import com.tenacious.forms.domain.*;
import com.tenacious.forms.service.dto.SurveyStatsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SurveyStats} and its DTO {@link SurveyStatsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SurveyStatsMapper extends EntityMapper<SurveyStatsDTO, SurveyStats> {


    @Mapping(target = "survey", ignore = true)
    SurveyStats toEntity(SurveyStatsDTO surveyStatsDTO);

    default SurveyStats fromId(Long id) {
        if (id == null) {
            return null;
        }
        SurveyStats surveyStats = new SurveyStats();
        surveyStats.setId(id);
        return surveyStats;
    }
}
