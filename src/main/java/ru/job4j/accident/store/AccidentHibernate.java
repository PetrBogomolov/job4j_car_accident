package ru.job4j.accident.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.job4j.accident.entity.Accident;
import ru.job4j.accident.entity.AccidentType;
import ru.job4j.accident.entity.Rule;

import java.util.List;
import java.util.function.Function;

public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Accident saveAccident(Accident accident, String[] ruleIds) {
        return transaction(session -> {
            for (String id : ruleIds) {
                accident.addRule(session.load(Rule.class, Integer.parseInt(id)));
            }
            session.save(accident);
            return accident;
        });
    }

    public void updateAccident(Accident accident, String[] ruleIds) {
        transaction(session -> {
            for (String id : ruleIds) {
                accident.addRule(session.load(Rule.class, Integer.parseInt(id)));
            }
            session.update(accident);
            return null;
        });
    }

    public void deleteAccidentById(int id) {
        transaction(session -> {
            session.delete(session.load(Accident.class, id));
            return null;
        });
    }

    public Accident getAccidentById(int id) {
        return transaction(session -> {
            Accident accident;
            accident = session.createQuery(
                    "select distinct ac from Accident ac join fetch ac.rules join fetch ac.type where ac.id = :id",
                    Accident.class
            ).setParameter("id", id).uniqueResult();
            return accident;
        });
    }

    public List<Accident> getAllAccidents() {
        try (Session session = sf.openSession()) {
            return session.createQuery("select distinct ac from Accident ac join fetch ac.rules join fetch ac.type").list();
        }
    }

    public List<Rule> getAllRules() {
        try (Session session = sf.openSession()) {
            return session.createQuery("from Rule").list();
        }
    }

    public List<AccidentType> getAllTypes() {
        try (Session session = sf.openSession()) {
            return session.createQuery("from AccidentType ").list();
        }
    }

    private <T> T transaction(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
