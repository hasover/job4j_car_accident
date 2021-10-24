package ru.job4j.accident.service;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class AccidentService {
    private final AccidentRepository accidentRepository;
    private final AccidentTypeRepository accidentTypeRepository;
    private final RuleRepository ruleRepository;

    public AccidentService(AccidentRepository accidentRepository,
                           AccidentTypeRepository accidentTypeRepository,
                           RuleRepository ruleRepository) {
        this.accidentRepository = accidentRepository;
        this.accidentTypeRepository = accidentTypeRepository;
        this.ruleRepository = ruleRepository;
    }

    public Collection<Accident> getAllAccidents() {
        return accidentRepository.findAll();
    }

    public Collection<AccidentType> getAllTypes() {
        List<AccidentType> res = new ArrayList<>();
        accidentTypeRepository.findAll().forEach(res::add);
        return res;
    }

    public Collection<Rule> getAllRules() {
        List<Rule> res = new ArrayList<>();
        ruleRepository.findAll().forEach(res::add);
        return res;
    }

    public void addAccident(Accident accident, int typeId, int[] rIds) {
        AccidentType accidentType = accidentTypeRepository.findById(typeId).get();
        accident.setType(accidentType);
        Arrays.stream(rIds).forEach(rId -> accident.addRule(ruleRepository.findById(rId).get()));
        accidentRepository.save(accident);
    }

    public Accident getAccidentById(int id) {
        Accident accident = accidentRepository.findById(id).get();
        return accident;
    }
}
