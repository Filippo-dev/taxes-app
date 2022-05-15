package com.globalblue.taxesapp.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.globalblue.taxesapp.utils.TaxesAppConstants.DIVISION_SCALE;
import static com.globalblue.taxesapp.utils.TaxesAppConstants.EURO_DECIMAL_PLACES;

public class MathOperations {
    public static BigDecimal safeDivide(BigDecimal numerator, BigDecimal denominator) {
        return numerator.divide(denominator, DIVISION_SCALE, RoundingMode.HALF_UP);
    }

    public static BigDecimal convertToEuro(BigDecimal rawValue) {
        return rawValue.setScale(EURO_DECIMAL_PLACES, RoundingMode.HALF_UP);
    }
}
