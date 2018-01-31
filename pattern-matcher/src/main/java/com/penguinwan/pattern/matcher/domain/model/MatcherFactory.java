package com.penguinwan.pattern.matcher.domain.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatcherFactory {
    public static Matcher newMatcher(Clause... clauses) {
        return new Matcher(clauses);
    }

    public static Matcher newMatcher(List<Clause> clauses) {
        return new Matcher(clauses);
    }

    public static ClauseBuilder clause() {
        return new ClauseBuilder();
    }

    public static ConditionBuilder condition() {
        return new ConditionBuilder();
    }

    public static class ClauseBuilder {
        private List<Condition> conditions = new ArrayList<Condition>();

        protected ClauseBuilder() {
        }

        public ClauseBuilder given(Condition... conditions) {
            this.conditions.addAll(Arrays.asList(conditions));
            return this;
        }

        public ClauseBuilder given(List<Condition> conditions) {
            this.conditions.addAll(conditions);
            return this;
        }

        public Clause then(Consequent consequent) {
            return new Clause(conditions, consequent);
        }
    }

    public static class ConditionBuilder {
        private String subject;
        private String value;

        protected ConditionBuilder() {
        }

        public ConditionBuilder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public ConditionBuilder value(String value) {
            this.value = value;
            return this;
        }

        public Condition build() {
            return new Condition(subject, value);
        }

    }
}
