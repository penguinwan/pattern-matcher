package com.penguinwan.pattern.matcher.application

import com.penguinwan.pattern.matcher.domain.model.Consequent
import com.penguinwan.pattern.matcher.domain.model.Input
import com.penguinwan.pattern.matcher.domain.model.MatcherRepository
import com.penguinwan.pattern.matcher.infrastructure.HibernateMatcherRepository
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
        SetupMatcherCommand.Clause clause1 = SetupMatcherCommand.Clause.of(
                SetupMatcherCommand.Consequent.of('place', 'igloo'),
                SetupMatcherCommand.Condition.of('animal', 'penguin'),
                SetupMatcherCommand.Condition.of('time', 'night'))
        SetupMatcherCommand.Clause clause2 = SetupMatcherCommand.Clause.of(
                SetupMatcherCommand.Consequent.of('place', 'field'),
                SetupMatcherCommand.Condition.of('animal', 'penguin'),
                SetupMatcherCommand.Condition.of('time', 'day'))

        SetupMatcherCommand command = new SetupMatcherCommand()
        command.getClauses().add(clause1)
        command.getClauses().add(clause2)

        long matcherId = matchingApplicationService.setup(command)
        Consequent consequent = matchingApplicationService.match(matcherId,
                new Input('animal', 'penguin'),
                new Input('time', 'night'))

        assert consequent.subject == 'place'
        assert consequent.value == 'igloo'
    }
}
