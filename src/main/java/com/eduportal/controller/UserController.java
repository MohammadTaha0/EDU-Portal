package com.eduportal.controller;

import com.eduportal.entities.User;
import com.eduportal.enums.Role;
import com.eduportal.service.JwtService;
import com.eduportal.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;

    @RolesAllowed("ROLE_ADMIN")
    @PostMapping("create/admin")
    public ResponseEntity<?> createAdmin(@RequestBody User admin) {
        if (admin != null && userService.createAdmin(admin)) {
            return ResponseEntity.ok("admin successfully created");
        }
        return ResponseEntity.internalServerError().body("something went wrong! try again later");
    }
    @RolesAllowed("ROLE_ADMIN")

    @GetMapping("/admin")
    public User getAllAdmin() {
        return userService.getAll(Role.ADMIN);
    }
    @RolesAllowed({"ROLE_ADMIN"})

    @PostMapping("/create/school")
    public ResponseEntity<?> createSchool(@RequestBody User school) {
        if (school != null && userService.createSchool(school)) {
            return ResponseEntity.ok("school successfully registered");
        }
        return ResponseEntity.internalServerError().body("something went wrong! try again later");
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody User user) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authenticate.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        }
        throw new UsernameNotFoundException("invalid user request");
    }

}
