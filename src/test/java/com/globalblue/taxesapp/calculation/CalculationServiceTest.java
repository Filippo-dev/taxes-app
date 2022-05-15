package com.globalblue.taxesapp.calculation;

import com.globalblue.taxesapp.exception.ApiRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CalculationServiceTest {

    CalculationService calculationService;

    @BeforeEach
    void setUp() {
        calculationService = new CalculationService();
    }

    @Test
    void itShouldValidateValidData() {
        // given
        String amount = "34.899";
        String vatRate = "13";

        // when
        // then
        calculationService.validateData(amount,vatRate); // -> no exceptions thrown
    }

    @Test
    void itShouldThrowIfAmountIsNotADouble() {
        // given
        String amount = "34.aa899";
        String vatRate = "13";

        // when
        // then
        assertThatThrownBy(() -> calculationService.validateData(amount,vatRate))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("missing or invalid (0 or non-numeric) amount input");
    }

    @Test
    void itShouldThrowIfVatRateIsNotAnInteger() {
        // given
        String amount = "34.899";
        String vatRate = "13.11";

        // when
        // then
        assertThatThrownBy(() -> calculationService.validateData(amount,vatRate))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("missing or invalid (0 or non-numeric) VAT rate input");
    }

    @Test
    void itShouldThrowIfVatRateIsNot10or13or20() {
        // given
        String amount = "34.899";
        String vatRate = "12";

        // when
        // then
        assertThatThrownBy(() -> calculationService.validateData(amount,vatRate))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining(vatRate + " is not a valid Austrian VAT rate");
    }

    @Test
    void itShouldGetGrossAndVATFromNet() {
        // given
        String net = "3499.99";
        String vatRate = "20";

        // when
        Calculation actualResult = calculationService.getFromNet(net, vatRate);

        // then
        Calculation expectedResult = new Calculation(new BigDecimal("3499.99"), "20%", new BigDecimal("700.00"), new BigDecimal("4199.99"));
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    //TODO: use the same logic of itShouldGetGrossAndVATFromNet to test getFromGross and getFromVAT
}