package com.bezkoder.payload;

import jakarta.validation.constraints.NotBlank;

//Spring Boot validate Request Body for nested object
public class AddressDTO {

    @NotBlank(message = "[Address] The city is required.")
    private String city;

    @NotBlank(message = "[Address] The street name is required.")
    private String street;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
