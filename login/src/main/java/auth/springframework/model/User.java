package auth.springframework.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "user_id")
    private UUID id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "active")
    private int active;

    public User() {
    }

    public User(String email, String password, String name, String lastName, int active) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.active = active;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

        public User build(){
            return new User( email, password, name, lastName, active);
        }
    }
}
