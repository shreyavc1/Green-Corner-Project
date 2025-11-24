package com.jsp.the_green_corner.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please provide country.")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Only upper and lowercase letters and spaces allowed.")
    private String country;

    @NotBlank(message = "Please provide your first name.")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Only upper and lowercase letters and spaces allowed.")
    private String firstName;

    @NotBlank(message = "Please provide your last name.")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Only upper and lowercase letters and spaces allowed.")
    private String lastName;

    @NotBlank(message = "Please provide your address.")
    @Pattern(regexp = "^[A-Za-z0-9 ,./-]+$",
            message = "Address can contain letters, numbers, spaces, commas, dots, slashes, and hyphens.")
    private String address;

    @NotBlank(message = "Please provide your state.")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Only upper and lowercase letters and spaces allowed.")
    private String state;

    @NotBlank(message = "Please provide your city.")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Only upper and lowercase letters and spaces allowed.")
    private String city;

    @NotNull(message = "Please provide your pincode.")
    @Min(value = 100000, message = "Pincode must be exactly 6 digits.")
    @Max(value = 999999, message = "Pincode must be exactly 6 digits.")
    private Long pinCode;

    @NotNull(message = "Please provide your contact number.")
    @Digits(integer = 10, fraction = 0, message = "Contact must be a 10-digit number!")
    @Min(value = 6000000000L, message = "Contact number must start with digits 6-9!")
    @Max(value = 9999999999L, message = "Contact must be a 10-digit number!")
    private Long contact;

    @NotBlank(message = "Provide shipping method.")
    private String shippingMethod;

    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
