package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;
import ru.job4j.accident.repository.AccidentJdbcTemplate;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;

@Service
public class AccidentService {
    private final AccidentHibernate accidentStore;

    public AccidentService(AccidentHibernate accidentStore) {
        this.accidentStore = accidentStore;
    }

    public Collection<Accident> getAllAccidents() {
        return accidentStore.getAllAccidents();
    }

    public Collection<AccidentType> getAllTypes() {
        return accidentStore.getAllTypes();
    }

    public Collection<Rule> getAllRules() {
        return accidentStore.getAllRules();
    }
    public void addAccident(Accident accident, int typeId, int[] rIds) {
         accidentStore.addAccident(accident, typeId, rIds);
    }

    public Accident getAccidentById(int id) {
        return accidentStore.getAccidentById(id);
    }

}
