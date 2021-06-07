package ru.job4j.accident.store;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private static final AtomicInteger ACCIDENT_ID = new AtomicInteger(1);
    private final Map<Integer, Accident> accidents = new HashMap<>();

    public Map<Integer, Accident> getAccidents() {
        return accidents;
    }

    public void add(Accident accident) {
        int currentId = ACCIDENT_ID.getAndIncrement();
        accident.setId(currentId);
        accidents.putIfAbsent(currentId, accident);
    }

    public void update(Accident accident) {
        accidents.replace(accident.getId(), accident);
    }

    public Accident getById(int id) {
        return accidents.get(id);
    }
}
