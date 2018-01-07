package com.penguinwan.pattern.matcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Matcher {
    private List<Clause> clauses = new ArrayList();

    Matcher(Clause... clauses) {
        this.clauses.addAll(Arrays.asList(clauses));
    }

    public Answer match(Input... inputs) {

        for (Clause clause : clauses) {
            boolean allMatch = true;
            int matchCount = 0;
            for (Input input : inputs) {
                for (Predicate predicate : clause.getPredicates()) {
                    if (predicate.getLeft().equals(input.getName())) {
                        if (!predicate.getRight().equals(input.getValue())) {
                            allMatch = false;
                            break;
                        } else {
                            matchCount++;
                        }
                    }
                }
            }

            if (allMatch && matchCount == clause.getPredicates().size()) {
                return clause.getAnswer();
            }
        }

        return Answer.NO_MATCH;
    }

}
