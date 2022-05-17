package com.globalblue.taxesapp.utils;

import java.math.BigDecimal;

import static com.globalblue.taxesapp.utils.TaxesAppConstants.DIVISION_SCALE;
import static com.globalblue.taxesapp.utils.TaxesAppConstants.EURO_DECIMAL_PLACES;
import static java.math.RoundingMode.CEILING;
import static java.math.RoundingMode.HALF_UP;

public class MathOperations {
    /**
     * @param numerator
     * Perform division with 50 maximum digits to the right of the point.
     * @param denominator
     * @return
     */
    public static BigDecimal safeDivide(BigDecimal numerator, BigDecimal denominator) {
        return numerator.divide(denominator, DIVISION_SCALE, HALF_UP);
    }

    /**
     * @param rawValue
     * Convert a Big Decimal to a euro value:
     * *Round to 2 decimal places,
     * *Force 2 decimal places
     * @return
     */
    public static BigDecimal convertToEuro(BigDecimal rawValue) {
        return rawValue.setScale(EURO_DECIMAL_PLACES, HALF_UP)
                .setScale(EURO_DECIMAL_PLACES, CEILING);
    }
}
