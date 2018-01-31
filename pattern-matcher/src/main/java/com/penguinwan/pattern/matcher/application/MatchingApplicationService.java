package com.penguinwan.pattern.matcher.application;

import com.penguinwan.pattern.matcher.domain.model.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MatchingApplicationService {
    private MatcherRepository matcherRepository;

    public MatchingApplicationService(MatcherRepository matcherRepository) {
        this.matcherRepository = matcherRepository;
    }

    public long setup(SetupMatcherCommand command) {
        List<Clause> clauses = command.getClauses().
                stream().
                map(SetupCommandToDomainModelTranslator::clause).
                collect(Collectors.toList());

        Matcher matcher = MatcherFactory.newMatcher(clauses);
        Matcher saved = matcherRepository.save(matcher);
        return saved.id();
    }

    public Consequent match(long matcherId, Input... inputs) throws MatcherNotFoundException {
        Optional<Matcher> matcher = matcherRepository.matcherOfId(matcherId);
        if (!matcher.isPresent()) {
            throw new MatcherNotFoundException();
        }

        return matcher.get().match(inputs);
    }

    private static final class SetupCommandToDomainModelTranslator {
        static Clause clause(SetupMatcherCommand.Clause clause) {
            return MatcherFactory.
                    clause().
                    given(SetupCommandToDomainModelTranslator.conditions(clause.getConditions())).
                    then(SetupCommandToDomainModelTranslator.consequent(clause.getConsequent()));
        }

        static List<Condition> conditions(List<SetupMatcherCommand.Condition> conditions) {
            return conditions.stream().map(SetupCommandToDomainModelTranslator::condition).collect(Collectors.toList());
        }

        static Condition condition(SetupMatcherCommand.Condition condition) {
            return MatcherFactory.condition().subject(condition.getSubject()).value(condition.getValue()).build();
        }

        static Consequent consequent(SetupMatcherCommand.Consequent consequent) {
            return new Consequent(consequent.getSubject(), consequent.getValue());
        }
    }
}
