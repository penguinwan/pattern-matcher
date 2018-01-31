package com.penguinwan.pattern.matcher.application;

import java.util.*;

public class SetupMatcherCommand {
    private List<Clause> clauses = new ArrayList();

    public List<Clause> getClauses() {
        return clauses;
    }

    public static class Clause {

        private List<Condition> conditions = new ArrayList<>();
        private Consequent consequent;

        public List<Condition> getConditions() {
            return conditions;
        }

        public Consequent getConsequent() {
            return consequent;
        }

        public static Clause of(Consequent consequent, Condition... conditions) {
            Clause clause = new Clause();
            clause.conditions.addAll(Arrays.asList(conditions));
            clause.consequent = consequent;
            return clause;
        }
    }

    public static class Condition {
        private String subject;
        private String value;

        public static Condition of(String subject, String value) {
            Condition condition = new Condition();
            condition.subject = subject;
            condition.value = value;
            return condition;
        }

        public String getSubject() {
            return subject;
        }

        public String getValue() {
            return value;
        }
    }

    public static class Consequent {
        private String subject;
        private String value;

        public static Consequent of(String subject, String value) {
            Consequent consequent = new Consequent();
            consequent.subject = subject;
            consequent.value = value;
            return consequent;
        }

        public String getSubject() {
            return subject;
        }

        public String getValue() {
            return value;
        }
    }
}
