package ru.job4j.accident.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.entity.User;
import ru.job4j.accident.store.RoleRepository;
import ru.job4j.accident.store.UserRepository;

@Service
public class RegAndAuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    public RegAndAuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @Transactional
    public User saveUser(User user) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(roleRepository.findByName("ROLE_USER"));
        return userRepository.save(user);
    }

    public User findUserByName(String name) {
       return userRepository.findByName(name);
    }
}
