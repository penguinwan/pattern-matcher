package com.penguinwan.pattern.matcher.application;

import com.penguinwan.matcher.shared.kernel.Clause;

import java.util.*;

public class SetupMatcherCommand {
    private List<Clause> clauses = new ArrayList();

    public List<Clause> getClauses() {
        return clauses;
    }
}
