package com.globalblue.taxesapp.calculation;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CalculationService {

    private static final List<String> VALID_VAT_RATES = List.of("10","13","20");

    public Calculation getFromNet(String net, String vatRate){
        validateData(net,vatRate);
        BigDecimal bigDecimalNet = new BigDecimal(net);
        BigDecimal bigDecimalVatRate = new BigDecimal(vatRate);

        BigDecimal bigDecimalVAT = bigDecimalNet.multiply(bigDecimalVatRate).divide(new BigDecimal("100"));
        BigDecimal bigDecimalGross = bigDecimalVAT.add(bigDecimalNet);

        return new Calculation(bigDecimalNet,bigDecimalGross,bigDecimalVAT,bigDecimalVatRate);
    }

    public Calculation getFromGross(String gross, String vatRate){
        return null;
    }

    public Calculation getFromVAT(String VAT, String vatRate){
        return null;
    }

    private void validateData(String amount, String vatRate){
        try{
            Double.parseDouble(amount);
        }catch (NumberFormatException e){
            throw new IllegalStateException("missing or invalid (0 or non-numeric) amount input");
        }
        try{
            Integer.parseInt(vatRate);
        }catch (NumberFormatException e){
            throw new IllegalStateException("missing or invalid (0 or non-numeric) VAT rate input");
        }
        if (!VALID_VAT_RATES.contains(vatRate)){
            throw new IllegalStateException(vatRate+" is not a valid Austrian VAT rate");
        }
    }
}
