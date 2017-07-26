package com.dragonfruit.dto;

import com.dragonfruit.Parseux.ColumnSeparator;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@ColumnSeparator(value = '\t')
@JsonPropertyOrder({"msisdn", "amount"})
public class TsvTestDTO {
    private String msisdn;
    private Double amount;

    public TsvTestDTO() {}

    public TsvTestDTO(final String msisdn, final Double amount) {
        this.msisdn = msisdn;
        this.amount = amount;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
