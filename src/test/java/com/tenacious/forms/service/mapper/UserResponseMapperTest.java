package com.tenacious.forms.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UserResponseMapperTest {

    private UserResponseMapper userResponseMapper;

    @BeforeEach
    public void setUp() {
        userResponseMapper = new UserResponseMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(userResponseMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(userResponseMapper.fromId(null)).isNull();
    }
}
