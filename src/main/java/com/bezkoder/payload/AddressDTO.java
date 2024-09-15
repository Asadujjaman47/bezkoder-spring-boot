package com.bezkoder.payload;

import jakarta.validation.constraints.NotBlank;

//Spring Boot validate Request Body for nested object
public class AddressDTO {

    @NotBlank(message = "[Address] The city is required.")
    private String city;

    @NotBlank(message = "[Address] The street name is required.")
    private String street;
}
