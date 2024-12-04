package org.SIP.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MutualFund {
    private String mutualFundId;
    private double pricePerunit;

    public MutualFund(String mutualFundId) {
        this.mutualFundId = mutualFundId;
        this.pricePerunit = 30;
    }
}
