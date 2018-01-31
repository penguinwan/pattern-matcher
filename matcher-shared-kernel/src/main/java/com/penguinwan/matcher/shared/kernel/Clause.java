package com.penguinwan.matcher.shared.kernel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Clause {
    private List<Condition> conditions = new ArrayList<>();
    private Consequent consequent;

    public List<Condition> getConditions() {
        return Collections.unmodifiableList(conditions);
    }

    public Consequent getConsequent() {
        return consequent;
    }

    private Clause(List<Condition> conditions, Consequent consequent) {
        this.conditions.addAll(conditions);
        this.consequent = consequent;
    }

    public static Clause of(Consequent consequent, Condition... conditions) {
        return new Clause(Arrays.asList(conditions), consequent);
    }
}
