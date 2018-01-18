package com.penguinwan.pattern.matcher.domain.model;

public class Input {
    private String name;
    private String value;

    public Input(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
