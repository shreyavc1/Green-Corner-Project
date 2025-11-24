package com.jsp.the_green_corner.entity;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Availability {
    IN_STOCK("In Stock"),
    OUT_OF_STOCK("Out of Stock"),
    LIMITED_STOCK("Limited Stock");

    private final String label;

    Availability(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }

    @JsonCreator
    public static Availability fromLabel(String label) {
        for (Availability a : Availability.values()) {
            if (a.label.equalsIgnoreCase(label)) {
                return a;
            }
        }
        throw new IllegalArgumentException("Invalid availability: " + label);
    }
}
