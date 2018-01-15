package com.penguinwan.pattern.matcher.domain.model;

public class Consequent {
    public static Consequent NO_MATCH = new Consequent("", "");
    private String subject;
    private String value;

    public Consequent(String subject, String value) {
        this.subject = subject;
        this.value = value;
    }
}
