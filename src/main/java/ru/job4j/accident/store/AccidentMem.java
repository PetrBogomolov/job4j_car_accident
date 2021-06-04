package ru.job4j.accident.store;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private static final AtomicInteger ACCIDENT_ID = new AtomicInteger();
    private final Map<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem() {
        add(new Accident(1, "Petr Bogomolov", "speeding", "Krasnodar"));
        add(new Accident(2, "Ivan Ivanov", "parking", "Moscow"));
    }

    public Map<Integer, Accident> getAccidents() {
        return accidents;
    }

    public void add(Accident accident) {
        accidents.putIfAbsent(ACCIDENT_ID.getAndIncrement(), accident);
    }
}
