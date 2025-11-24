package com.jsp.the_green_corner.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy= "seller", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<Equipments> equipments;
}
