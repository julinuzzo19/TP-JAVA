package com.api.books.service;

import com.api.books.model.Role;
import com.api.books.model.User;
import com.api.books.repository.RoleRepository;
import com.api.books.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service("userService")
public class UserService {

    public static final String ADMIN = "ADMIN";

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmailToClient(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        creteUserRole(user);
        return userRepository.save(user);
    }

    private void creteUserRole(User user) {
        Role userRole = roleRepository.findByRole(ADMIN);
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
    }

}