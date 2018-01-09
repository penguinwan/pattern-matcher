package com.penguinwan.pattern.matcher;

public class Condition {
    private String subject;
    private String value;

    Condition(String subject, String value) {
        this.subject = subject;
        this.value = value;
    }

    public String getSubject() {
        return subject;
    }

    public String getValue() {
        return value;
    }
}
