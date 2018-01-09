package com.penguinwan.pattern.matcher

import static com.penguinwan.pattern.matcher.MatcherFactory.*

class MatcherTest extends spock.lang.Specification {
    void setup() {
    }

    void cleanup() {
    }

    def "able to answer when input match all predicate"() {
        given:
        Matcher matcher = newMatcher(
                clause().given(
                        predicate().left("animal").right("penguin").build(),
                        predicate().left("time").right("night").build()
                ).then(new Answer("place", "igloo"))
        )

        when:
        Answer answer = matcher.match(new Input("animal", "penguin"), new Input("time", "night"))

        then:
        answer.value == "igloo"
    }

    def "able to answer correct when multiple clause"() {
        given:
        Matcher matcher = newMatcher(
                clause().given(
                        predicate().left("animal").right("penguin").build(),
                        predicate().left("time").right("day").build()
                ).then(
                        new Answer("place", "sea")
                ),
                clause().given(
                        predicate().left("animal").right("tiger").build(),
                        predicate().left("time").right("day").build()
                ).then(
                        new Answer("place", "savannah")
                ),
                clause().given(
                        predicate().left("animal").right("penguin").build(),
                        predicate().left("time").right("night").build()
                ).then(
                        new Answer("place", "igloo")
                )
        )

        when:
        Answer answer = matcher.match(new Input("animal", "penguin"), new Input("time", "night"))

        then:
        answer.value == "igloo"
    }

    def "answer first found when multiple clause match"() {
        given:
        Matcher matcher = newMatcher(
                clause().given(
                        predicate().left("animal").right("penguin").build(),
                        predicate().left("time").right("night").build()
                ).then(
                        new Answer("place", "igloo")
                ),
                clause().given(
                        predicate().left("animal").right("penguin").build(),
                        predicate().left("time").right("night").build()
                ).then(
                        new Answer("place", "savannah")
                )
        )

        when:
        Answer answer = matcher.match(new Input("animal", "penguin"), new Input("time", "night"))

        then:
        answer.value == "igloo"
    }

    def "NO_MATCH when input match partial predicate"() {
        given:
        Matcher matcher = newMatcher(
                clause().given(
                        predicate().left("animal").right("penguin").build(),
                        predicate().left("time").right("night").build()
                ).then(new Answer("place", "igloo"))
        )

        when:
        Answer answer = matcher.match(new Input("animal", "penguin"), new Input("time", "day"))

        then:
        answer == Answer.NO_MATCH
    }

    def "NO_MATCH when pass in partial input"() {
        given:
        Matcher matcher = newMatcher(
                clause().given(
                        predicate().left("animal").right("penguin").build(),
                        predicate().left("time").right("night").build()
                ).then(new Answer("place", "igloo"))
        )

        when:
        Answer answer = matcher.match(new Input("animal", "penguin"))

        then:
        answer == Answer.NO_MATCH
    }

    def "NO_MATCH when pass in non-existence input"() {
        given:
        Matcher matcher = newMatcher(
                clause().given(
                        predicate().left("animal").right("penguin").build(),
                        predicate().left("time").right("night").build()
                ).then(new Answer("place", "igloo"))
        )

        when:
        Answer answer = matcher.match(new Input("animal", "penguin"), new Input("nonexistence", "a"))

        then:
        answer == Answer.NO_MATCH
    }
}
