package com.jsp.the_green_corner.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MoistureRequirement {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High");

    private final String label;

    MoistureRequirement(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }

    @JsonCreator
    public static MoistureRequirement fromLabel(String label) {
        for (MoistureRequirement m : MoistureRequirement.values()) {
            if (m.label.equalsIgnoreCase(label)) {
                return m;
            }
        }
        throw new IllegalArgumentException("Invalid moisture: " + label);
    }
}

