package com.ddd.membership.domain.pointhst.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.ddd.membership.domain.pointhst.api.PointHstController;
import com.ddd.membership.domain.pointhst.application.PointHstService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PointHstController.class)
public class PointHstControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PointHstService pointHstService;

    @DisplayName("[Controller] 포인트 내역조회 테스트")
    @Test
    void testGetHsts() throws Exception {

        mockMvc.perform(get("/membership/getHsts")
                .param("barcodeNo", "123456789")
                .param("startTm", "2022-12-01 10:00:00")
                .param("endTm", "2022-12-31 10:00:00")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
