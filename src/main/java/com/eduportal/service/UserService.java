package com.eduportal.service;

import com.eduportal.entities.User;
import com.eduportal.enums.Role;
import com.eduportal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Boolean createAdmin(User user) {
        user.setRoles(Role.ADMIN);
        user.setCreatedAt(Instant.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }
    public User getAll(Role roles){
        return userRepository.findByRoles(roles);
    }
    public Boolean createSchool(User user) {
        user.setRoles(Role.SCHOOL);
        user.setCreatedAt(Instant.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }
}
