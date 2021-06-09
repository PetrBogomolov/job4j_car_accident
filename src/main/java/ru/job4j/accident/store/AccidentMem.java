package ru.job4j.accident.store;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private static final AtomicInteger ACCIDENT_ID = new AtomicInteger(1);
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final Map<Integer, AccidentType> types = new HashMap<>();
    private final Map<Integer, Rule> rules = new HashMap<>();

    public AccidentMem() {
        types.put(0, AccidentType.of(0, "не выбрано"));
        types.put(1, AccidentType.of(1, "Превышение скорости"));
        types.put(2, AccidentType.of(2, "Обгон в неположенном месте"));
        types.put(3, AccidentType.of(3, "Стоянка в запрещенном месте"));
        types.put(4, AccidentType.of(4, "Нарушение разметки"));
        rules.put(0, Rule.of(0, "статья не выбрана"));
        rules.put(1, Rule.of(1, "статья 12.9(скорость)"));
        rules.put(2, Rule.of(2, "статья 12.15(обгон)"));
        rules.put(3, Rule.of(3, "статья 12.19(стоянка)"));
        rules.put(4, Rule.of(4, "статья 12.16(разметка)"));
    }

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public Collection<AccidentType> getAllTypes() {
        return types.values();
    }

    public Collection<Rule> getAllRules() {
        return rules.values();
    }

    public void add(Accident accident, String[] rIds) {
        Set<Rule> rulesForCurrentAccident = new HashSet<>();
        for (String ruleId : rIds) {
            rulesForCurrentAccident.add(getRuleById(Integer.parseInt(ruleId)));
        }
        int currentId = ACCIDENT_ID.getAndIncrement();
        accident.setId(currentId);
        accident.setType(getTypeById(accident.getType().getId()));
        accident.setRules(rulesForCurrentAccident);
        accidents.putIfAbsent(currentId, accident);
    }

    public void update(Accident accident, String[] rIds) {
        Set<Rule> rulesForCurrentAccident = new HashSet<>();
        for (String ruleId : rIds) {
            rulesForCurrentAccident.add(getRuleById(Integer.parseInt(ruleId)));
        }
        accident.setRules(rulesForCurrentAccident);
        accident.setType(getTypeById(accident.getType().getId()));
        accidents.replace(accident.getId(), accident);
    }

    public Accident getAccidentById(int id) {
        return accidents.get(id);
    }

    public AccidentType getTypeById(int id) {
        return types.get(id);
    }

    public Rule getRuleById(int id) {
        return rules.get(id);
    }
}
