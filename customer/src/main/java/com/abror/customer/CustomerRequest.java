package com.abror.customer;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRequest {
    String firstName;
    String lastName;
    String email;
}
