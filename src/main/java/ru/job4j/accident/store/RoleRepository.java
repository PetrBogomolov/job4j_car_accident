package ru.job4j.accident.store;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findByName(String name);
}
