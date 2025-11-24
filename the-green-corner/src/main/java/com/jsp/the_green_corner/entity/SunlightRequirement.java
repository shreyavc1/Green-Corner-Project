package com.jsp.the_green_corner.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SunlightRequirement {
    FULL_SUN("Full Sun"),
    PARTIAL_SHADE("Partial Shade"),
    FULL_SHADE("Full Shade");

    private final String label;

    SunlightRequirement(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }

    @JsonCreator
    public static SunlightRequirement fromLabel(String label) {
        for (SunlightRequirement s : SunlightRequirement.values()) {
            if (s.label.equalsIgnoreCase(label)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Invalid Sunlight Requirement: " + label);
    }
}
