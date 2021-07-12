package ru.job4j.accident.store;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByName(String name);
}
