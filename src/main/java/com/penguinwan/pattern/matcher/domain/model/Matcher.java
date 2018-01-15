package com.penguinwan.pattern.matcher.domain.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Matcher {
    private List<Clause> clauses = new ArrayList();

    Matcher(Clause... clauses) {
        this.clauses.addAll(Arrays.asList(clauses));
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
