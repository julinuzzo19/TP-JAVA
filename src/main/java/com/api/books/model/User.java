package com.api.books.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "active")
    private int active;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User() {
    }

    public User(String email, String password, String name, String lastName, int active, Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.active = active;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private int id;
        private String email;
        private String password;
        private String name;
        private String lastName;
        private int active;
        private Set<Role> roles;

        public Builder id(int id){
            this.id = id;
            return this;
        }


        public Builder email(String email){
            this.email = email;
            return this;
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "id=" + id +
                    ", email='" + email + '\'' +
                    ", password='" + password + '\'' +
                    ", name='" + name + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", active=" + active +
                    '}';
        }

        public Builder password(String password){
            this.password = password;
            return this;
        }
        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder lastName(String lastName){
            this.lastName = lastName;
            return this;
        }
        public Builder active(int active){
            this.active = active;
            return this;
        }
        public Builder roles(Set<Role> roles){
            this.roles = roles;
            return this;
        }

        public User build(){
            return new User( email, password, name, lastName, active,roles);
        }
    }
}
