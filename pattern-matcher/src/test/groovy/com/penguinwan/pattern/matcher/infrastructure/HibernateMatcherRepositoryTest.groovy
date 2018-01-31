package com.penguinwan.pattern.matcher.infrastructure

import com.penguinwan.pattern.matcher.domain.model.Consequent
import com.penguinwan.pattern.matcher.domain.model.Matcher
import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import static com.penguinwan.pattern.matcher.domain.model.MatcherFactory.*

class HibernateMatcherRepositoryTest {
    static SessionFactory sessionFactory
    HibernateMatcherRepository matcherRepository

    @BeforeClass
    static void beforeClass() {
        Configuration config = new Configuration()
        sessionFactory = config.configure().buildSessionFactory()
    }

    @Before
    void setup() {
        matcherRepository = new HibernateMatcherRepository(sessionFactory)
    }

    @Test
    void "able to persist and retrieve from database"() {
        Matcher transientMatcher = newMatcher(
                clause().
                        given(
                                condition().subject('susi').value('lily').build()).
                        then(
                                new Consequent('consequent-susi', 'consequent-lily')))

        Matcher savedMatcher = matcherRepository.save(transientMatcher)

        Optional<Matcher> retrievedMatcher = matcherRepository.matcherOfId(savedMatcher.id())

        assert retrievedMatcher.present == true
        assert retrievedMatcher.get().clauses[0].conditions[0].subject == 'susi'
        assert retrievedMatcher.get().clauses[0].conditions[0].value == 'lily'
        assert retrievedMatcher.get().clauses[0].consequent.subject == 'consequent-susi'
        assert retrievedMatcher.get().clauses[0].consequent.value == 'consequent-lily'
    }

    @Test
    void "not able to retrieve matcher given a wrong id"() {
        Optional<Matcher> retrievedMatcher = matcherRepository.matcherOfId(-1)

        assert retrievedMatcher.present == false
    }

    @Test
    void "able to persist multiple clause"() {
        Matcher transientMatcher = newMatcher(
                clause().
                        given(
                                condition().subject('susi').value('lily').build()
                        ).
                        then(
                                new Consequent('consequent-susi', 'consequent-lily')),
                clause().
                        given(
                                condition().subject('mimi').value('zaza').build()
                        ).
                        then(
                                new Consequent('consequent-mimi', 'consequent-zaza'))
        )

        Matcher savedMatcher = matcherRepository.save(transientMatcher)

        Optional<Matcher> retrievedMatcher = matcherRepository.matcherOfId(savedMatcher.id())

        assert retrievedMatcher.present == true
        assert retrievedMatcher.get().clauses.size() == 2
        assert retrievedMatcher.get().clauses[1].conditions[0].subject == 'mimi'
        assert retrievedMatcher.get().clauses[1].conditions[0].value == 'zaza'
        assert retrievedMatcher.get().clauses[1].consequent.subject == 'consequent-mimi'
        assert retrievedMatcher.get().clauses[1].consequent.value == 'consequent-zaza'
    }

    @Test
    void 'able to persist multiple condition'() {
        Matcher transientMatcher = newMatcher(
                clause().
                        given(
                                condition().subject('susi').value('lily').build(),
                                condition().subject('mimi').value('zaza').build(),
                                condition().subject('titi').value('sasa').build()
                        ).
                        then(
                                new Consequent('consequent-susi', 'consequent-lily')))

        Matcher savedMatcher = matcherRepository.save(transientMatcher)

        Optional<Matcher> retrievedMatcher = matcherRepository.matcherOfId(savedMatcher.id())

        assert retrievedMatcher.present == true
        assert retrievedMatcher.get().clauses[0].conditions.size() == 3
        assert retrievedMatcher.get().clauses[0].conditions[0].subject == 'susi'
        assert retrievedMatcher.get().clauses[0].conditions[1].subject == 'mimi'
        assert retrievedMatcher.get().clauses[0].conditions[2].subject == 'titi'
    }

}
