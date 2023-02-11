package com.ddd.membership.domain.user.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.ddd.membership.domain.user.application.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;

    @DisplayName("[Controller] 바코드 생성 테스트")
    @Test
    void testCreateBarcode() throws Exception {
        String content = "{\"userId\": \"1\"}";

        mockMvc.perform(post("/membership/createBarcode")
            .contentType(MediaType.APPLICATION_JSON)
            .content(content))
            .andExpect(status().isOk())
            .andDo(print())
            .andReturn()
            ;
    }

    @DisplayName("[Controller] 유저 생성 테스트")
    @Test
    void testCreateUser() throws Exception {

        String content = "{\"name\": \"Ted\", \"pwd\": \"123123\"}";

        mockMvc.perform(post("/membership/createUser")
            .contentType(MediaType.APPLICATION_JSON)
            .content(content))
            .andExpect(status().isOk())
            .andDo(print())
            .andReturn()
            ;
        }
}
