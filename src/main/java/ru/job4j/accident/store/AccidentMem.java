package ru.job4j.accident.store;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private static final AtomicInteger ACCIDENT_ID = new AtomicInteger(1);
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final Map<Integer, AccidentType> types = new HashMap<>();

    public AccidentMem() {
        types.put(0, AccidentType.of(0, "не выбрано"));
        types.put(1, AccidentType.of(1, "Превышение скорости"));
        types.put(2, AccidentType.of(2, "Обгон в неположенном месте"));
        types.put(3, AccidentType.of(3, "Стоянка в запрещенном месте"));
        types.put(4, AccidentType.of(4, "Нарушение разметки"));
    }

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public Collection<AccidentType> getAllTypes() {
        return types.values();
    }

    public void add(Accident accident) {
        int currentId = ACCIDENT_ID.getAndIncrement();
        accident.setId(currentId);
        accident.setType(getTypeById(accident.getType().getId()));
        accidents.putIfAbsent(currentId, accident);
    }

    public void update(Accident accident) {
        accident.setType(getTypeById(accident.getType().getId()));
        accidents.replace(accident.getId(), accident);
    }

    public Accident getAccidentById(int id) {
        return accidents.get(id);
    }

    public AccidentType getTypeById(int id) {
        return types.get(id);
    }
}
