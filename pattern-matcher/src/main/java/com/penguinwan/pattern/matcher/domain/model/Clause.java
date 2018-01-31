package com.penguinwan.pattern.matcher.domain.model;

import java.util.List;

public class Clause {
    private long id;
    private List<Condition> conditions;
    private Consequent consequent;

    public Clause() {
    }

    Clause(List<Condition> conditions, Consequent consequent) {
        this.conditions = conditions;
        this.consequent = consequent;
    }

    public long id() {
        return id;
    }

    List<Condition> getConditions() {
        return conditions;
    }

    Consequent getConsequent() {
        return consequent;
    }

}
