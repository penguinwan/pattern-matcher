package com.penguinwan.pattern.matcher.application

import com.penguinwan.pattern.matcher.domain.model.Input
import com.penguinwan.pattern.matcher.domain.model.MatcherRepository
import com.penguinwan.pattern.matcher.infrastructure.HibernateMatcherRepository
import com.penguinwan.matcher.shared.kernel.*
import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration
import org.junit.Before
import org.junit.Test

class MatchingApplicationServiceTest {
    MatcherRepository matcherRepository
    SessionFactory sessionFactory
    MatchingApplicationService matchingApplicationService

    @Before
    void setup() {
        sessionFactory = new Configuration().configure().buildSessionFactory()
        matcherRepository = new HibernateMatcherRepository(sessionFactory)
        matchingApplicationService = new MatchingApplicationService(matcherRepository)
    }

    @Test
    void "able to setup and match"() {
        Clause clause1 = Clause.of(
                Consequent.of('place', 'igloo'),
                Condition.of('animal', 'penguin'),
                Condition.of('time', 'night'))
        Clause clause2 = Clause.of(
                Consequent.of('place', 'field'),
                Condition.of('animal', 'penguin'),
                Condition.of('time', 'day'))

        SetupMatcherCommand command = new SetupMatcherCommand()
        command.getClauses().add(clause1)
        command.getClauses().add(clause2)

        long matcherId = matchingApplicationService.setup(command)
        com.penguinwan.pattern.matcher.domain.model.Consequent consequent = matchingApplicationService.match(matcherId,
                new Input('animal', 'penguin'),
                new Input('time', 'night'))

        assert consequent.subject == 'place'
        assert consequent.value == 'igloo'
    }
}
