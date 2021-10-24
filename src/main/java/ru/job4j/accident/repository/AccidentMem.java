package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class AccidentMem implements AccidentStore {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final Map<Integer, AccidentType> types = new ConcurrentHashMap<>();
    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(3);

    public AccidentMem() {
        types.put(1, AccidentType.of(1, "Две машины"));
        types.put(2, AccidentType.of(2, "Машина и человек"));
        types.put(3, AccidentType.of(3, "Машина и велосипед"));

        rules.put(1, Rule.of(1, "Статья. 1"));
        rules.put(2, Rule.of(2, "Статья. 2"));
        rules.put(3, Rule.of(3, "Статья. 3"));

        accidents.put(1, new Accident(1, "Accident1", "Cars crashed #1", "Address1", types.get(1)));
        accidents.put(2, new Accident(2, "Accident2", "Cars crashed #2", "Address2", types.get(2)));
        accidents.put(3, new Accident(3, "Accident3", "Cars crashed #3", "Address3", types.get(3)));

        accidents.get(1).addRule(rules.get(1));
        accidents.get(2).addRule(rules.get(2));
        accidents.get(3).addRule(rules.get(3));
    }

    public Collection<Accident> getAllAccidents() {
        return accidents.values();
    }

    public Collection<AccidentType> getAllTypes() {
        return types.values();
    }

    public Collection<Rule> getAllRules() {
        return rules.values();
    }

    public void addAccident(Accident accident, int typeId, int[] rIds) {
        if (accident.getId() == 0) {
            int id = counter.incrementAndGet();
            accident.setId(id);
        }
        accident.setType(types.get(typeId));
        Arrays.stream(rIds).forEach(i -> accident.addRule(rules.get(i)));
        accidents.put(accident.getId(), accident);
    }

    public Accident getAccidentById(int id) {
        return accidents.get(id);
    }
}
