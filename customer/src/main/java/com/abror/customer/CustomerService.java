package com.abror.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.core.Response;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;


    public void register(CustomerRequest customerRequest) {
        Customer customer = Customer.builder()
                .email(customerRequest.getEmail())
                .firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .build();

        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );

        System.out.println(fraudCheckResponse);

        if (fraudCheckResponse.getIsFraudster())
            throw new IllegalStateException("it is a fraudster");
        else {
            ResponseEntity<String> response = restTemplate.postForEntity(
                    "http://RABBITMQ/api/v1/rabbit-mq",
                    customer.toString(),
                    String.class
            );

            System.out.println(response.getBody());
            System.out.println(response.getStatusCode());

        }

    }


}
