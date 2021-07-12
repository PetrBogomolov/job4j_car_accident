package ru.job4j.accident.store;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.entity.Accident;

import java.util.List;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @Query("select distinct ac from Accident ac join fetch ac.rules join fetch ac.type")
    List<Accident> findAllAccidents();
}
