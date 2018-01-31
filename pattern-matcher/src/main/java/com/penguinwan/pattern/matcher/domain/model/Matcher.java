package com.penguinwan.pattern.matcher.domain.model;

import java.util.*;


public class Matcher {
    private long id;
    private List<Clause> clauses = new ArrayList();

    public Matcher() {
    }

    Matcher(Clause... clauses) {
        this.clauses.addAll(Arrays.asList(clauses));
    }

    Matcher(List<Clause> clauses) {
        this.clauses.addAll(clauses);
    }

    public long id() {
        return id;
    }

    public Consequent match(Input... inputs) {

        for (Clause clause : clauses) {
            boolean allMatch = true;
            int matchCount = 0;
            for (Input input : inputs) {
                for (Condition condition : clause.getConditions()) {
                    if (condition.getSubject().equals(input.getName())) {
                        if (!condition.getValue().equals(input.getValue())) {
                            allMatch = false;
                            break;
                        } else {
                            matchCount++;
                        }
                    }
                }
            }

            if (allMatch && matchCount == clause.getConditions().size()) {
                return clause.getConsequent();
            }
        }

        return Consequent.NO_MATCH;
    }

}
