package com.eduportal.repositories;

import com.eduportal.entities.User;
import com.eduportal.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByRoles(Role roles);
}
