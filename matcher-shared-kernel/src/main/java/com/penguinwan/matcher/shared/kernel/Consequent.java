package com.penguinwan.matcher.shared.kernel;

public class Consequent {
    private String subject;
    private String value;

    private Consequent(String subject, String value) {
        this.subject = subject;
        this.value = value;
    }

    public static Consequent of(String subject, String value) {
        return new Consequent(subject, value);

    }

    public String getSubject() {
        return subject;
    }

    public String getValue() {
        return value;
    }
}
