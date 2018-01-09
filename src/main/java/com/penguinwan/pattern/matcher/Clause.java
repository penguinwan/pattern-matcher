package com.penguinwan.pattern.matcher;

import java.util.List;

public class Clause {
    private List<Condition> conditions;

    private Answer answer;

    Clause(List<Condition> conditions, Answer answer) {
        this.conditions = conditions;
        this.answer = answer;
    }

    List<Condition> getConditions() {
        return conditions;
    }

    Answer getAnswer() {
        return answer;
    }

}
