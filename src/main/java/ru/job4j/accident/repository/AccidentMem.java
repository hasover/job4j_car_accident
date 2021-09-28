package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(3);

    public AccidentMem() {
        accidents.put(1, new Accident(1,"Accident1", "Cars crashed #1", "Address1"));
        accidents.put(2, new Accident(2,"Accident2", "Cars crashed #2", "Address2"));
        accidents.put(3, new Accident(3,"Accident3", "Cars crashed #3", "Address3"));
    }

    public Collection<Accident> getAllAccidents() {
        return accidents.values();
    }

    public void addAccident(Accident accident) {
        if (accident.getId() == 0) {
            int id = counter.incrementAndGet();
            accident.setId(id);
        }
        accidents.put(accident.getId(), accident);
    }

    public Accident getAccidentById(int id) {
        return accidents.get(id);
    }
}
