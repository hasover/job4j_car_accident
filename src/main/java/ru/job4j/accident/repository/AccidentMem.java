package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    public AccidentMem() {
        accidents.put(1, Accident.of("Accident1", "Cars crashed #1", "Address1"));
        accidents.put(2, Accident.of("Accident2", "Cars crashed #2", "Address2"));
        accidents.put(3, Accident.of("Accident3", "Cars crashed #3", "Address3"));
    }

    public Collection<Accident> getAllAccidents() {
        return accidents.values();
    }
}
