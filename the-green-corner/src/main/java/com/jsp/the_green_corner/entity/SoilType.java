package com.jsp.the_green_corner.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SoilType {
    LOAMY_SOIL("Loamy soil"),
    SANDY_SOIL("Sandy soil"),
    CLAY_SOIL("Clay soil");

    private final String label;

    SoilType(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }

    @JsonCreator
    public static SoilType fromLabel(String label) {
        for (SoilType s : SoilType.values()) {
            if (s.label.equalsIgnoreCase(label)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Invalid soil type: " + label);
    }
}

