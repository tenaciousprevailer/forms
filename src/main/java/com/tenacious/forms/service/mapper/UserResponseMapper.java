package com.tenacious.forms.service.mapper;


import com.tenacious.forms.domain.*;
import com.tenacious.forms.service.dto.UserResponseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserResponse} and its DTO {@link UserResponseDTO}.
 */
@Mapper(componentModel = "spring", uses = {SurveyMapper.class, QuestionMapper.class})
public interface UserResponseMapper extends EntityMapper<UserResponseDTO, UserResponse> {

    @Mapping(source = "survey.id", target = "surveyId")
    @Mapping(source = "question.id", target = "questionId")
    UserResponseDTO toDto(UserResponse userResponse);

    @Mapping(source = "surveyId", target = "survey")
    @Mapping(source = "questionId", target = "question")
    UserResponse toEntity(UserResponseDTO userResponseDTO);

    default UserResponse fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserResponse userResponse = new UserResponse();
        userResponse.setId(id);
        return userResponse;
    }
}
