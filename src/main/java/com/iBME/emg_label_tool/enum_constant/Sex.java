package com.iBME.emg_label_tool.enum_constant;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Sex {
    MALE("MALE"),
    FEMALE("FEMALE");
    private final String value;

    Sex(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
