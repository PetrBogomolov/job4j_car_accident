package ru.job4j.accident.store;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.entity.Rule;

public interface RuleRepository extends CrudRepository<Rule, Integer> {
}
