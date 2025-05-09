package com.abror.fraud;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FraudCheckResponse {
    private Boolean isFraudster;
    public FraudCheckResponse(boolean isFraudulentCustomer) {
        this.isFraudster = isFraudulentCustomer;
    }
}
