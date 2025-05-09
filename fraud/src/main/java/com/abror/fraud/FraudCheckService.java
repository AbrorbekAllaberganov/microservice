package com.abror.fraud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FraudCheckService {
    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public boolean isFraudulentCustomer(Integer customerId){
        FraudCheckoutHistory fraud = FraudCheckoutHistory.builder()
                .customerId(customerId)
                .isFraudster(false)
                .build();

        fraudCheckHistoryRepository.save(fraud);
        return false;
    }


}
