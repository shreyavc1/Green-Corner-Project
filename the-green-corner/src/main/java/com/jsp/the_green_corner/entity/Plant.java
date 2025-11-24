package com.jsp.the_green_corner.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
// using this to stop recursive call in json response and apply serialization
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Data
@Entity
public class Plant{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Plant name field cannot be blank!")
    private String name;

    @NotBlank(message = "Description field name cannot be blank!")
    private String description;

    @NotNull(message = "Price field cannot be null!")
    private double price;

    private double discountPrice;

    @NotNull(message = "Rating field cannot be null!")
    private double rating;

    @OneToMany(mappedBy = "plant",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("plant")
    private List<Review> reviews= new ArrayList<>();


    private long totalSalesLastMonth;

    @NotBlank(message = "Seller name field cannot be blank!")
    private String sellerName;

//    @OneToOne
    @Embedded
    private Address sellerAddress;

    @Enumerated(EnumType.STRING)
    private Availability availability;

    @NotNull(message = "Please provide plant's available quantity!")
    private long quantityAvailable;

    @ElementCollection
    private List<String> categories;

    @Enumerated(EnumType.STRING)
    private SunlightRequirement sunlightRequirement;

    @Enumerated(EnumType.STRING)
    private MoistureRequirement moistureRequirement;

    @Enumerated(EnumType.STRING)
    private SoilType soilType;

    private String season;

    @Enumerated(EnumType.STRING)
    private GrowthRate growthRate;

    @Enumerated(EnumType.STRING)
    private PotSizeRequired potSizeRequired;

    private String genus;
    private String localName;
    private String regionalName;
    private String biologicalName;
    private String botanicalName;

    @ElementCollection
    private  List<String> tags;

    @ElementCollection
    private List<String> shippingStates;

    private String primaryImage;

    @ElementCollection
    private List<String> secondaryImages;

    private String shoppingPolicy;
    private String refundPolicy;

}
