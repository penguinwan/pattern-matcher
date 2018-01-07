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

    public static PredicateBuilder predicate() {
        return new PredicateBuilder();
    }

    static class ClauseBuilder {
        private List<Predicate> predicates = new ArrayList<Predicate>();
        public ClauseBuilder given(Predicate... predicates) {
            this.predicates.addAll(Arrays.asList(predicates));
            return this;
        }
        public Clause then(Answer answer) {
            return new Clause(predicates, answer);
        }
    }

    static class PredicateBuilder {
        private String left;
        private String right;

        public PredicateBuilder left(String left) {
            this.left = left;
            return this;
        }

        public PredicateBuilder right(String right) {
            this.right = right;
            return this;
        }

        public Predicate build() {
            return new Predicate(left, right);
        }

    }
}
