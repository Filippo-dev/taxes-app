package com.globalblue.taxesapp.utils;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.globalblue.taxesapp.utils.MathOperations.convertToEuro;
import static com.globalblue.taxesapp.utils.MathOperations.safeDivide;
import static org.assertj.core.api.Assertions.assertThat;

class MathOperationsTest {

    @Test
    void itShouldDivideSafely() {
        // given
        BigDecimal numerator = new BigDecimal("23.9865");
        BigDecimal denominator = new BigDecimal("4.901907");

        // when
        BigDecimal actual = safeDivide(numerator, denominator);

        // then
        BigDecimal expected = new BigDecimal("4.89329968928419082614174442721985545625406601961237");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void itShouldConvertBigDecimalToEuro() {
        // given
        BigDecimal doubleBigDecimal = new BigDecimal("85.0872");
        BigDecimal integerBigDecimal = new BigDecimal("23");

        // when
        BigDecimal actualDoubleEuro = convertToEuro(doubleBigDecimal);
        BigDecimal actualIntegerEuro = convertToEuro(integerBigDecimal);

        // then
        BigDecimal expectedDoubleEuro = new BigDecimal("85.09");
        BigDecimal expectedIntegerEuro = new BigDecimal("23.00");

        assertThat(actualDoubleEuro).isEqualTo(expectedDoubleEuro);
        assertThat(actualIntegerEuro).isEqualTo(expectedIntegerEuro);
    }
}