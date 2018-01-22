package com.penguinwan.pattern.matcher.application;

import com.penguinwan.pattern.matcher.domain.model.*;

import java.util.Optional;

public class MatchingApplicationService {
    private MatcherRepository matcherRepository;

    public MatchingApplicationService(MatcherRepository matcherRepository) {
        this.matcherRepository = matcherRepository;
    }

    public Consequent match(long matcherId, Input... inputs) throws MatcherNotFoundException {
        Optional<Matcher> matcher = matcherRepository.matcherOfId(matcherId);
        if(!matcher.isPresent()) {
            throw new MatcherNotFoundException();
        }

        return matcher.get().match(inputs);
    }
}
