package ru.job4j.accident.store;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.job4j.accident.entity.Accident;
import ru.job4j.accident.entity.AccidentType;
import ru.job4j.accident.entity.Rule;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Accident add(Accident accident, String[] ids) {
        jdbc.update("insert into accidents (name, text, address, type_id, rule_ids) values (?, ?, ?, ?, ?)",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                Arrays.toString(ids));
        return accident;
    }

    public void update(Accident accident, String[] ids) {
        jdbc.update("UPDATE accidents SET name = (?), text = (?), address = (?), type_id = (?), rule_ids = (?) WHERE id = (?)",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                Arrays.toString(ids),
                accident.getId());
    }

    public void delete(int id) {
        jdbc.update("delete from accidents where id = ?", id);
    }

    public Accident getAccidentById(int id) {
        return  jdbc.queryForObject(
                "select id, name, text, address, type_id, rule_ids from accidents where id = ?",
                accidentRowMapper,
                id
        );
    }

    public List<Accident> getAllAccidents() {
        return jdbc.query("select * from accidents", accidentRowMapper);
    }

    public List<Rule> getAllRules() {
        return jdbc.query("select * from rules", new BeanPropertyRowMapper<>(Rule.class));

    }

    public List<AccidentType> getAllTypes() {
        return jdbc.query("select * from accidentTypes", new BeanPropertyRowMapper<>(AccidentType.class));
    }

    private AccidentType getTypeById(int id) {
        return  jdbc.queryForObject(
                "select id, name from accidentTypes where id = ?",
                new BeanPropertyRowMapper<>(AccidentType.class),
                id
        );
    }

    private Rule getRuleById(int id) {
        return  jdbc.queryForObject(
                "select id, name from rules where id = ?",
                new BeanPropertyRowMapper<>(Rule.class),
                id
        );
    }

    private final RowMapper<Accident> accidentRowMapper = ((resultSet, i) -> {
        Set<Rule> rulesByCurrentAccident = new HashSet<>();
        String rIds = resultSet.getString("rule_ids");
        rIds = rIds.substring(1, rIds.length() - 1);
        for (String id : rIds.split(",")) {
            rulesByCurrentAccident.add(getRuleById(Integer.parseInt(id.trim())));
        }
        Accident accident = new Accident(
                (resultSet.getString("name")),
                (resultSet.getString("text")),
                (resultSet.getString("address")),
                getTypeById(resultSet.getInt("type_id")));
                accident.setRules(rulesByCurrentAccident);
        accident.setId(resultSet.getInt("id"));
        return accident;
    });
}
