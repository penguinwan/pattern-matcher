package com.penguinwan.pattern.matcher

class MatcherTest extends spock.lang.Specification {
    void setup() {
    }

    void cleanup() {
    }

    def "able to answer when input match all predicate"() {
        given:
//       Matcher matcher = new Matcher(
//               Clause.ClauseBuilder.given(
//                       left("name").operator(Operator.EQUALS).right("susi").build()
//               ).then(
//                       answer("age", "30")
//               )
//       )
        Matcher matcher = new Matcher(
                new Clause(
                        new Answer("place", "igloo"),
                        new Predicate("animal", "penguin"),
                        new Predicate("time", "night")
                )
        )

        when:
//        List<Answer> answers = matcher.match(
//                inputFor("name").is("susi")
//        )
        Answer answer = matcher.match(new Input("animal", "penguin"), new Input("time", "night"))

        then:
        answer.value == "igloo"
    }

    def "NO_MATCH when input match partial predicate"() {
        given:
        Matcher matcher = new Matcher(
                new Clause(
                        new Answer("place", "igloo"),
                        new Predicate("animal", "penguin"),
                        new Predicate("time", "night")
                )
        )

        when:
        Answer answer = matcher.match(new Input("animal", "penguin"), new Input("time", "day"))

        then:
        answer == Answer.NO_MATCH
    }

    def "NO_MATCH when pass in partial input"() {
        given:
        Matcher matcher = new Matcher(
                new Clause(
                        new Answer("place", "igloo"),
                        new Predicate("animal", "penguin"),
                        new Predicate("time", "night")
                )
        )

        when:
        Answer answer = matcher.match(new Input("animal", "penguin"))

        then:
        answer == Answer.NO_MATCH
    }
}
