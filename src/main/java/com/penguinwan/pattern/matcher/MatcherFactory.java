package com.penguinwan.pattern.matcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatcherFactory {
    public static Matcher newMatcher(Clause... clauses) {
        return new Matcher(clauses);
    }

    public static ClauseBuilder clause() {
        return new ClauseBuilder();
    }

    public static ConditionBuilder condition() {
        return new ConditionBuilder();
    }

    static class ClauseBuilder {
        private List<Condition> conditions = new ArrayList<Condition>();
        public ClauseBuilder given(Condition... conditions) {
            this.conditions.addAll(Arrays.asList(conditions));
            return this;
        }
        public Clause then(Answer answer) {
            return new Clause(conditions, answer);
        }
    }

    static class ConditionBuilder {
        private String subject;
        private String value;

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
