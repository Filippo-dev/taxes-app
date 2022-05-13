package com.globalblue.taxesapp.calculation;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Calculation {
    private BigDecimal net;
    private BigDecimal gross;
    private BigDecimal VAT;
    private BigDecimal vatRate;
}
