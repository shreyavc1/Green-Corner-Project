package com.jsp.the_green_corner.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDateTime;

// to stop recursive call in json response and apply serialization
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "reviewId"
)
@Data
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reviewId;

    @ManyToOne
    @JsonIgnore
    private User user;

    @NotBlank(message="username cannot be blank.")
    @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "Username can contain uppercase lowercase letters and one space only!")
    private String username;

    @ManyToOne
    @JsonIgnore
    private Equipments equipment;

    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
    @JsonIgnore
    private Plant plant;

    @NotNull(message="Please give some ratings!")
    private double rating;

    @NotBlank(message="Please add a comment!")
    private String comment;

    @NotNull(message = "Please mention if product is delivered or not(true/false)!")
    private boolean productDelivered;

    @CurrentTimestamp
    private LocalDateTime dateTime;

    private long likes;

    private long dislikes;

}
