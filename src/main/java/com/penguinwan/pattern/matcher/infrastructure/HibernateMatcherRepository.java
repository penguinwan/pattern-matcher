package com.penguinwan.pattern.matcher.infrastructure;

import com.penguinwan.pattern.matcher.domain.model.Matcher;
import com.penguinwan.pattern.matcher.domain.model.MatcherRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

public class HibernateMatcherRepository implements MatcherRepository {
    private SessionFactory sessionFactory;

    public HibernateMatcherRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Matcher save(Matcher matcher) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(matcher);

        transaction.commit();
        session.close();
        return matcher;
    }

    public Optional<Matcher> matcherOfId(long matcherId) {
        Session session = sessionFactory.openSession();

        Matcher matcher = session.get(Matcher.class, matcherId);

        session.close();
        return Optional.ofNullable(matcher);
    }
}
