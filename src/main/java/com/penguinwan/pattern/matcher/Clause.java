package com.penguinwan.pattern.matcher;

import java.util.List;

public class Clause {
    private List<Predicate> predicates;

    private Answer answer;

    Clause(List<Predicate> predicates, Answer answer) {
        this.predicates = predicates;
        this.answer = answer;
    }

    List<Predicate> getPredicates() {
        return predicates;
    }

    Answer getAnswer() {
        return answer;
    }

}
