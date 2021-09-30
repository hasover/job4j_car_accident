package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;

public interface AccidentStore {
    Collection<Accident> getAllAccidents();
    Collection<AccidentType> getAllTypes();
    Collection<Rule> getAllRules();
    void addAccident(Accident accident, int typeId, int[] rIds);
    Accident getAccidentById(int id);
}
