package com.tenacious.forms.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tenacious.forms.web.rest.TestUtil;

public class UserResponseTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserResponse.class);
        UserResponse userResponse1 = new UserResponse();
        userResponse1.setId(1L);
        UserResponse userResponse2 = new UserResponse();
        userResponse2.setId(userResponse1.getId());
        assertThat(userResponse1).isEqualTo(userResponse2);
        userResponse2.setId(2L);
        assertThat(userResponse1).isNotEqualTo(userResponse2);
        userResponse1.setId(null);
        assertThat(userResponse1).isNotEqualTo(userResponse2);
    }
}
