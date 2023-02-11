package com.ddd.membership.domain.point.api;

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

import com.ddd.membership.domain.point.application.PointService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PointController.class)
public class PointControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PointService pointService;

    @DisplayName("[Controller] 포인트 적립 테스트")
    @Test
    void testSavePoint() throws Exception {
        String content = "{\"partnerId\": \"lkm\", \"barcodeNo\" : \"123456789\", \"savePoint\" : 1000}";

        mockMvc.perform(post("/membership/savePoint")
            .contentType(MediaType.APPLICATION_JSON)
            .content(content))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @DisplayName("[Controller] 포인트 사용 테스트")
    @Test
    void testUsePoint() throws Exception {
        String content = "{\"partnerId\": \"lkm\", \"barcodeNo\" : \"123456789\", \"usePoint\" : 2000}";

        mockMvc.perform(post("/membership/usePoint")
            .contentType(MediaType.APPLICATION_JSON)
            .content(content))
            .andExpect(status().isOk())
            .andDo(print());
        }
}
