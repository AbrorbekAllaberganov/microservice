package com.abror.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FraudCheckResponse {
    private Boolean isFraudster;
    public FraudCheckResponse(boolean isFraudulentCustomer) {
        this.isFraudster = isFraudulentCustomer;
    }
}
