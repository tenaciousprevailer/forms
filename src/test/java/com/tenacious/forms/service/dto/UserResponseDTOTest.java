package com.tenacious.forms.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tenacious.forms.web.rest.TestUtil;

public class UserResponseDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserResponseDTO.class);
        UserResponseDTO userResponseDTO1 = new UserResponseDTO();
        userResponseDTO1.setId(1L);
        UserResponseDTO userResponseDTO2 = new UserResponseDTO();
        assertThat(userResponseDTO1).isNotEqualTo(userResponseDTO2);
        userResponseDTO2.setId(userResponseDTO1.getId());
        assertThat(userResponseDTO1).isEqualTo(userResponseDTO2);
        userResponseDTO2.setId(2L);
        assertThat(userResponseDTO1).isNotEqualTo(userResponseDTO2);
        userResponseDTO1.setId(null);
        assertThat(userResponseDTO1).isNotEqualTo(userResponseDTO2);
    }
}
