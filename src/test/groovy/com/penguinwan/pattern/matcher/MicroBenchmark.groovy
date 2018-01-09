package com.penguinwan.pattern.matcher;

import org.junit.Test

import static com.penguinwan.pattern.matcher.MatcherFactory.clause
import static com.penguinwan.pattern.matcher.MatcherFactory.newMatcher
import static com.penguinwan.pattern.matcher.MatcherFactory.predicate;

//TODO: this is not fair comparison, the first test always take longer time
class MicroBenchmark {
    @Test
    void "normal"() {
        long startTime = System.currentTimeMillis()
        100000.times {
            Matcher matcher = newMatcher(
                    clause().given(
                            predicate().left("animal").right("penguin").build(),
                            predicate().left("time").right("night").build()
                    ).then(new Answer("place", "igloo"))
            )

            Answer answer = matcher.match(new Input("animal", "penguin"), new Input("time", "night"))
        }
        long endTime = System.currentTimeMillis()
        println(endTime - startTime)
    }

    @Test
    void "another functional"() {
        long startTime = System.currentTimeMillis()
        100000.times {
            Matcher matcher = newMatcher(
                    clause().given(
                            predicate().left("animal").right("penguin").build(),
                            predicate().left("time").right("night").build()
                    ).then(new Answer("place", "igloo"))
            )

            Answer answer = matcher.mostPromisingFunction(new Input("animal", "penguin"), new Input("time", "night"))
        }
        long endTime = System.currentTimeMillis()
        println(endTime - startTime)
    }

    @Test
    void "low performance"() {
        long startTime = System.currentTimeMillis()
        100000.times {
            Matcher matcher = newMatcher(
                    clause().given(
                            predicate().left("animal").right("penguin").build(),
                            predicate().left("time").right("night").build()
                    ).then(new Answer("place", "igloo"))
            )
            HashMap input = [
                    "animal": "penguin",
                    "time"  : "night"
            ]
            matcher.functionalLowPerform(input)
        }
        long endTime = System.currentTimeMillis()
        println(endTime - startTime)
    }

    @Test
    void "high performance"() {
        long startTime = System.currentTimeMillis()
        100000.times {
            Matcher matcher = newMatcher(
                    clause().given(
                            predicate().left("animal").right("penguin").build(),
                            predicate().left("time").right("night").build()
                    ).then(new Answer("place", "igloo"))
            )
            HashMap input = [
                    "animal": "penguin",
                    "time"  : "night"
            ]
            matcher.functionalHighPerform(input)
        }
        long endTime = System.currentTimeMillis()
        println(endTime - startTime)
    }
}
