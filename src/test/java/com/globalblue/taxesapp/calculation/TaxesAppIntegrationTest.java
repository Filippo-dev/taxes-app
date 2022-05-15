package com.globalblue.taxesapp.calculation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;

import static org.assertj.core.api.Fail.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TaxesAppIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void itShouldGetGrossAndVATFromNet() throws Exception {
        // given
        String net = "6805.11";
        String vatRate = "13";
        Calculation expectedResult = new Calculation(new BigDecimal("6805.11"), "13%", new BigDecimal("884.66"), new BigDecimal("7689.77"));

        // when
        ResultActions resultActions = mockMvc.perform(get("/api/v1/calculations/getFromNet?net=" + net + "&vatRate=" + vatRate));

        // then
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().json(objectToJson(expectedResult)));
    }

    @Test
    void itShouldGetErrorMessageIfVatRateIsNotValid() throws Exception {
        // given
        String net = "6805.11";
        String vatRate = "15";

        // when
        ResultActions resultActions = mockMvc.perform(get("/api/v1/calculations/getFromNet?net=" + net + "&vatRate=" + vatRate));

        // then
        resultActions.andExpect(status().is4xxClientError());
    }

    //TODO: Use the same logic of the 2 previous methods to test the other 2 endpoints and error cases.

    private String objectToJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            fail("failed to convert object");
            return null;
        }
    }
}
