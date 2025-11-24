package com.jsp.the_green_corner.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
//I am using this to stop recursive call in reviews in json response and apply serialization
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Data
@Entity
public class Equipments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name field cannot be blank.")
    private String name;

    @NotBlank(message = "Description field cannot be blank. Give some description!")
    private String description;

    @NotNull(message = "Price field cannot be empty.")
    private double price;

    @NotNull(message="Give some ratings!")
    private double rating;

    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"equipment", "plant", "user"})
    private List<Review> reviews = new ArrayList<>();

    @ElementCollection
    private List<String> categories;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Seller seller;

    @Enumerated(EnumType.STRING)
    private Availability availability;

    private long quantityAvailable;

    @ElementCollection
    private List<String> tags;

    private String shippingPolicy;

    private String refundPolicy;

    private String primaryImage;

    @ElementCollection
    private List<String> secondaryImages;
}
