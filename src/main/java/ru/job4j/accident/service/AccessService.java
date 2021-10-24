package ru.job4j.accident.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.User;
import ru.job4j.accident.repository.AuthorityRepository;
import ru.job4j.accident.repository.UserRepository;

@Service
public class AccessService {
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;

    public AccessService(PasswordEncoder passwordEncoder,
                         UserRepository userRepository,
                         AuthorityRepository authorityRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    public void saveUser(User user) {
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthority(authorityRepository.findByAuthority("ROLE_USER"));
        userRepository.save(user);
    }
}
