package com.penguinwan.pattern.matcher;

public class Predicate {
    private String left;
    private String right;

    Predicate(String left, String right) {
        this.left = left;
        this.right = right;
    }

    public String getLeft() {
        return left;
    }

    public String getRight() {
        return right;
    }
}
