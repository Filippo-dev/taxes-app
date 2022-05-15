package com.globalblue.taxesapp.calculation;

import com.globalblue.taxesapp.exception.ApiRequestException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.globalblue.taxesapp.utils.MathOperations.convertToEuro;
import static com.globalblue.taxesapp.utils.MathOperations.safeDivide;
import static com.globalblue.taxesapp.utils.TaxesAppConstants.ONE_HUNDRED;
import static com.globalblue.taxesapp.utils.TaxesAppConstants.VALID_VAT_RATES;
import static java.math.BigDecimal.ONE;

@Service
public class CalculationService {

    /**
     * @param net
     * @param vatRate
     * Calculate gross and VAT
     * @return
     */
    public Calculation getFromNet(String net, String vatRate) {
        validateData(net, vatRate);

        // Convert Strings to Big Decimals
        BigDecimal bigDecimalNet = new BigDecimal(net);
        BigDecimal bigDecimalVatRate = new BigDecimal(vatRate).divide(ONE_HUNDRED);

        // VAT = net * vatRate
        BigDecimal bigDecimalVAT = bigDecimalNet.multiply(bigDecimalVatRate);

        // gross = VAT + net
        BigDecimal bigDecimalGross = bigDecimalVAT.add(bigDecimalNet);

        return new Calculation(convertToEuro(bigDecimalNet), vatRate + "%", convertToEuro(bigDecimalVAT), convertToEuro(bigDecimalGross));
    }

    /**
     * @param gross
     * @param vatRate
     * Calculate VAT and net
     * @return
     */
    public Calculation getFromGross(String gross, String vatRate) {
        validateData(gross, vatRate);

        // Convert Strings to Big Decimals
        BigDecimal bigDecimalGross = new BigDecimal(gross);
        BigDecimal bigDecimalVatRate = new BigDecimal(vatRate).divide(ONE_HUNDRED);

        // net = gross / (1 + vatRate)
        BigDecimal bigDecimalNet = safeDivide(bigDecimalGross, ONE.add(bigDecimalVatRate));

        // VAT = gross - net
        BigDecimal bigDecimalVAT = bigDecimalGross.subtract(bigDecimalNet);

        // set 2 decimal places
        return new Calculation(convertToEuro(bigDecimalNet), vatRate + "%", convertToEuro(bigDecimalVAT), convertToEuro(bigDecimalGross));
    }

    /**
     * @param VAT
     * @param vatRate
     * Calculate net and gross
     * @return
     */
    public Calculation getFromVAT(String VAT, String vatRate) {
        validateData(VAT, vatRate);

        // Convert Strings to Bid Decimals
        BigDecimal bigDecimalVAT = new BigDecimal(VAT);
        BigDecimal bigDecimalVatRate = new BigDecimal(vatRate).divide(ONE_HUNDRED);

        // net = VAT / vatRate
        BigDecimal bigDecimalNet = safeDivide(bigDecimalVAT, bigDecimalVatRate);

        // gross = VAT + net
        BigDecimal bigDecimalGross = bigDecimalVAT.add(bigDecimalNet);

        // set 2 decimal places
        return new Calculation(convertToEuro(bigDecimalNet), vatRate + "%", convertToEuro(bigDecimalVAT), convertToEuro(bigDecimalGross));
    }

    private void validateData(String amount, String vatRate) {
        try {
            Double.parseDouble(amount);
        } catch (NumberFormatException e) {
            throw new ApiRequestException("missing or invalid (0 or non-numeric) amount input");
        }

        try {
            Integer.parseInt(vatRate);
        } catch (NumberFormatException e) {
            throw new ApiRequestException("missing or invalid (0 or non-numeric) VAT rate input");
        }

        if (!VALID_VAT_RATES.contains(vatRate)) {
            throw new ApiRequestException(vatRate + " is not a valid Austrian VAT rate");
        }
    }
}
