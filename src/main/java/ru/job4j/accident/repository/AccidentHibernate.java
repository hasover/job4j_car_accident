package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;

public class AccidentHibernate implements AccidentStore {

    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    private<T> T tx(Function<Session, T> command) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            T result =  command.apply(session);
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public Collection<Accident> getAllAccidents() {
        return tx(session -> session.createQuery("select distinct a from Accident a " +
                "join fetch a.rules join fetch a.type", Accident.class).list());
    }

    @Override
    public Collection<AccidentType> getAllTypes() {
        return tx(session -> session.createQuery("from AccidentType").list());
    }

    @Override
    public Collection<Rule> getAllRules() {
        return tx(session -> session.createQuery("from Rule").list());
    }

    @Override
    public void addAccident(Accident accident, int typeId, int[] rIds) {
        if (accident.getId() == 0) {
            addNewAccident(accident, typeId, rIds);
        } else {
            updateAccident(accident);
        }
    }

    private void updateAccident(Accident accident) {
        tx(session -> {
            Accident accident1 = session.get(Accident.class, accident.getId());
            accident1.setName(accident.getName());
            accident1.setText(accident.getText());
            accident1.setAddress(accident.getAddress());
            return null;
        });
    }

    private void addNewAccident(Accident accident, int typeId, int[] rIds) {
        tx(session -> {
            AccidentType accidentType = session.get(AccidentType.class, typeId);
            accident.setType(accidentType);
            Arrays.stream(rIds).forEach(rId -> accident.addRule(session.get(Rule.class, rId)));
            session.save(accident);
            return null;
        });
    }

    @Override
    public Accident getAccidentById(int id) {
        return tx(session -> session.createQuery("select distinct a from Accident a " +
                "join fetch a.rules join fetch a.type where a.id =:param", Accident.class)
                .setParameter("param", id).uniqueResult());
    }
}
