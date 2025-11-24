package com.jsp.the_green_corner.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum GrowthRate {
    SLOW("Slow"),
    MODERATE("Moderate"),
    FAST("Fast");

    private final String label;

    GrowthRate(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }

    @JsonCreator
    public static GrowthRate fromLabel(String label) {
        for (GrowthRate g : GrowthRate.values()) {
            if (g.label.equalsIgnoreCase(label)) {
                return g;
            }
        }
        throw new IllegalArgumentException("Invalid growth rate: " + label);
    }
}

