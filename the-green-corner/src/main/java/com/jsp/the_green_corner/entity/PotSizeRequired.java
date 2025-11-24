package com.jsp.the_green_corner.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PotSizeRequired {
    SMALL("Small"),
    MEDIUM("Medium"),
    LARGE("Large");

    private final String label;

    PotSizeRequired(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }

    @JsonCreator
    public static PotSizeRequired fromLabel(String label) {
        for (PotSizeRequired p : PotSizeRequired.values()) {
            if (p.label.equalsIgnoreCase(label)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Invalid pot size: " + label);
    }
}

