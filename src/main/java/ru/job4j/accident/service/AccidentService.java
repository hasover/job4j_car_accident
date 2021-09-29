package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;

@Service
public class AccidentService {
    private final AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public Collection<Accident> getAllAccidents() {
        return accidentMem.getAllAccidents();
    }

    public Collection<AccidentType> getAllTypes() {
        return accidentMem.getAllTypes();
    }

    public Collection<Rule> getAllRules() {
        return accidentMem.getAllRules();
    }
    public void addAccident(Accident accident, int typeId, int[] rIds) {
         accidentMem.addAccident(accident, typeId, rIds);
    }

    public Accident getAccidentById(int id) {
        return accidentMem.getAccidentById(id);
    }


}
