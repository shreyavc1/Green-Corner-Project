package com.jsp.the_green_corner.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Cart{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @ManyToMany
    private List<Plant> plants = new ArrayList<>();

    @ManyToMany
    private List<Equipments> equipment = new ArrayList<>();


}