package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.store.AccidentMem;

import java.util.Collection;

@Service
public class AccidentService {

    private final AccidentMem store;

    public AccidentService(AccidentMem store) {
        this.store = store;
    }

    public void addAccident(Accident accident) {
        store.add(accident);
    }

    public void updateAccident(Accident accident) {
       store.update(accident);
    }

    public Accident getAccidentById(int id) {
        return store.getById(id);
    }

    public Collection<Accident> getAllValues() {
        return store.getAccidents();
    }
}
