package com.penguinwan.pattern.matcher.domain.model;

import java.util.List;

public class Clause {
    private List<Condition> conditions;

    private Consequent consequent;

    Clause(List<Condition> conditions, Consequent consequent) {
        this.conditions = conditions;
        this.consequent = consequent;
    }

    List<Condition> getConditions() {
        return conditions;
    }

    Consequent getConsequent() {
        return consequent;
    }

}
