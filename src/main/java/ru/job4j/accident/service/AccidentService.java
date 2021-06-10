package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.store.AccidentJdbcTemplate;
import java.util.List;

@Service
public class AccidentService {

    private final AccidentJdbcTemplate store;

    public AccidentService(AccidentJdbcTemplate store) {
        this.store = store;
    }

    public void addAccident(Accident accident, String[] rIds) {
        store.add(accident, rIds);
    }

    public void updateAccident(Accident accident, String[] rIds) {
       store.update(accident, rIds);
    }

    public void deleteAccident(int id) {
        store.delete(id);
    }

    public Accident getAccidentById(int id) {
        return store.getAccidentById(id);
    }

    public List<Accident> getAllValues() {
        return store.getAllAccidents();
    }

    public List<AccidentType> getAllTypes() {
        return store.getAllTypes();
    }

    public List<Rule> getAllRules() {
        return store.getAllRules();
    }
}
