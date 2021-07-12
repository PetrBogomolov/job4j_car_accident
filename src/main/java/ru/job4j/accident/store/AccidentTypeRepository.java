package ru.job4j.accident.store;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.entity.AccidentType;

public interface AccidentTypeRepository extends CrudRepository<AccidentType, Integer> {
}
