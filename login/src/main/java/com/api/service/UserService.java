package com.api.service;

import com.api.dto.CreateUserRequest;
import com.api.dto.LoginRequest;
import com.api.dto.LoginResponse;
import com.api.exception.SystemLoginException;
import com.api.model.Role;
import com.api.model.User;
import com.api.repository.RoleRepository;
import com.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.Arrays;
import java.util.HashSet;

@Service("userService")
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

        //createRole();
    }

    //private void createRole() {
    //   roleRepository.save(new Role("ADMIN"));
    // }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(CreateUserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());
        user.setActive(1);
        Role userRole = roleRepository.findByRole("USER");

        System.out.println("Welcome");
        System.out.println(userRole.getRole());
        user.setRoles(new HashSet<Rfole>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getUser());
        if(user == null){
            throw new SystemLoginException("");
        }else if(user.getPassword().equals(loginRequest.getPassword())){
            return new LoginResponse(user.getName(),user.getRoles());
        }
        throw new SystemLoginException("");
    }
}