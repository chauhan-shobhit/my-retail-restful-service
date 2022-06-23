package com.myretail.product.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Price implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigDecimal value;
    private String currency_code;

    public Price(BigDecimal value, String currency_code) {
        this.value = value.setScale(2, RoundingMode.CEILING);
        this.currency_code = currency_code;
    }

    /**
     * @return BigDecimal return the value
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(BigDecimal value) {
        this.value = value.setScale(2, RoundingMode.DOWN);
    }

    /**
     * @return String return the currency_code
     */
    public String getcurrency_code() {
        return currency_code;
    }

    /**
     * @param currency_code the currency_code to set
     */
    public void setcurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

}
