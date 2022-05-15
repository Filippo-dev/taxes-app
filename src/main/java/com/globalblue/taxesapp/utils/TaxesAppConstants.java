package com.globalblue.taxesapp.utils;

import java.math.BigDecimal;
import java.util.List;

public class TaxesAppConstants {
    public static final List<String> VALID_VAT_RATES = List.of("10", "13", "20");
    public static final BigDecimal ONE_HUNDRED = new BigDecimal("100");
    public static final int DIVISION_SCALE = 50;
    public static final int EURO_DECIMAL_PLACES = 2;
}
