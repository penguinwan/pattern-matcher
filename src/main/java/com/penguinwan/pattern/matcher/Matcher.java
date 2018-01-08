package com.penguinwan.pattern.matcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

    public Answer functionalLowPerform(HashMap inputs) {
        for (Clause clause : clauses) {
            java.util.function.Predicate andPredicate = null;
            List<java.util.function.Predicate> functions = new ArrayList<>();
            for (Predicate predicate : clause.getPredicates()) {
                java.util.function.Predicate<HashMap> equalsPredicate = (hashMapInput) -> {
                    boolean containsKey = hashMapInput.containsKey(predicate.getLeft());
                    //System.out.println("containsKey key " + predicate.getLeft() + " result " + containsKey);
                    if (containsKey) {
                        boolean sameValue = hashMapInput.get(predicate.getLeft()).equals(predicate.getRight());
                        //System.out.println("input " + hashMapInput.get(predicate.getLeft()) + " spec " + predicate.getRight() + " result " + sameValue);
                        return containsKey && sameValue;
                    } else {
                        return false;
                    }
                };

                if (andPredicate == null) {
                    andPredicate = equalsPredicate;
                } else {
                    andPredicate = andPredicate.and(equalsPredicate);
                }
            }
            andPredicate.test(inputs);

            //System.out.println("result " + andPredicate.test(inputs));
        }
        return Answer.NO_MATCH;
    }

    public Answer functionalHighPerform(HashMap inputs) {
        java.util.function.BiPredicate andPredicate = null;
        Clause specClause = clauses.get(0);
        for (Predicate predicate : specClause.getPredicates()) {
            java.util.function.BiPredicate<HashMap, HashMap> equalsPredicate = (hashMapSpec, hashMapInput) -> {
                    String key = predicate.getLeft();
                    if(hashMapSpec.containsKey(key) && hashMapInput.containsKey(key)) {
                        boolean sameValue = hashMapInput.get(key).equals(hashMapSpec.get(key));
                        //System.out.println("sameValue "+sameValue);
                        return sameValue;
                    } else {
                        //System.out.println("containsKey false "+key);
                        return false;
                    }
            };

            if(andPredicate==null) {
                andPredicate = equalsPredicate;
            } else {
                andPredicate = andPredicate.and(equalsPredicate);
            }
        }

        for(Clause clause : clauses) {
            HashMap hashMapSpec = new HashMap();
            for(Predicate predicate : clause.getPredicates()) {
                hashMapSpec.put(predicate.getLeft(), predicate.getRight());
            }
            //System.out.println("result "+andPredicate.test(hashMapSpec, inputs));
            andPredicate.test(hashMapSpec, inputs);
        }

        return Answer.NO_MATCH;

    }

}
