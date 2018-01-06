package com.penguinwan.pattern.matcher;

public class Answer {
    public static Answer NO_MATCH = new Answer("", "");
    private String name;
    private String value;

    public Answer(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
