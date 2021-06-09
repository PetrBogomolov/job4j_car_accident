package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.store.AccidentMem;

import java.util.Collection;

@Service
public class AccidentService {

    private final AccidentMem store;

    public AccidentService(AccidentMem store) {
        this.store = store;
    }

    public void addAccident(Accident accident, String[] rIds) {
        store.add(accident, rIds);
    }

    public void updateAccident(Accident accident, String[] rIds) {
       store.update(accident, rIds);
    }

    public Accident getAccidentById(int id) {
        return store.getAccidentById(id);
    }

    public AccidentType getTypeById(int id) {
        return store.getTypeById(id);
    }

    public Collection<Accident> getAllValues() {
        return store.getAccidents();
    }

    public Collection<AccidentType> getAllTypes() {
        return store.getAllTypes();
    }

    public Collection<Rule> getAllRules() {
        return store.getAllRules();
    }
}
