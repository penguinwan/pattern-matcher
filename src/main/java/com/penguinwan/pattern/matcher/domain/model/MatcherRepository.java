package com.penguinwan.pattern.matcher.domain.model;

import java.util.Optional;

public interface MatcherRepository {
    Matcher save(Matcher matcher);

    Optional<Matcher> matcherOfId(long id);
}