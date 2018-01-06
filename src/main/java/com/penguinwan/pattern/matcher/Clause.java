package com.penguinwan.pattern.matcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Clause {
    private List<Predicate> predicates = new ArrayList();

    private Answer answer;

    public Clause(Answer answer, Predicate... predicates) {
        this.predicates.addAll(Arrays.asList(predicates));
        this.answer = answer;
    }

    public List<Predicate> getPredicates() {
        return predicates;
    }

    public Answer getAnswer() {
        return answer;
    }
}
