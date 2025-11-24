package com.jsp.the_green_corner.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Entity
@Data
@Table(name = "PlannedUser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Username field must not be blank!")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters!")
    @Pattern(regexp = "^[A-Za-z0-9 ]+$", message="Username must contain letters,numbers and spaces only" +
            " and username must be unique!")
    @Column(unique = true)
    private String name;

    @NotBlank(message ="password field must not be blank!")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%&*!]).{7,20}$",
            message = "Password must be 7–20 chars long, include at least one uppercase, lowercase, digit, " +
                    "and one special char (@#$%&*!).")

    private String password;

    @Pattern(regexp = "^[a-z0-9]+(?:[._-][a-z0-9]+)?@[a-z]+(?:\\.[a-z]{2,})+$",
            message = "Email must be lowercase and follow a valid format")
    @NotBlank(message ="Email field must not be blank!")
    @Column(unique = true)
    private String email;

    @NotNull(message ="Contact field must not be blank!")
    @Digits(integer = 10, fraction = 0, message = "Contact must be a 10-digit number!")
    @Min(value = 6000000000L, message = "Contact number must start with digits 6-9!")
    @Max(value = 9999999999L, message = "Contact number must be a valid 10-digit number!")
    @Column(unique = true)
    private Long contact;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"user", "equipment", "plant"})
    private List<Review> reviews = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;

    @OneToOne(cascade = CascadeType.ALL)
    private WishList wishList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Delivery> deliveries=new ArrayList<>();

}
