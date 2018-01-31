package com.penguinwan.matcher.shared.kernel;

public class Condition {
    private String subject;
    private String value;

    private Condition(String subject, String value) {
        this.subject = subject;

        this.value = value;
    }

    public String getSubject() {
        return subject;
    }

    public String getValue() {
        return value;
    }

    public static Condition of(String subject, String value) {
        return new Condition(subject, value);
    }
}