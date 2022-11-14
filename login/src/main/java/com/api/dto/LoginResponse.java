package com.api.dto;

import com.api.model.Role;

import java.util.Set;

public class LoginResponse {

    private String user;
    private Set<Role> roles;

    public LoginResponse(String user, Set<Role> roles) {
        this.user = user;
        this.roles = roles;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
