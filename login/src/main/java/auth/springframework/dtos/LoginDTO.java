package auth.springframework.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginDTO {
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

        String email;
        String password;
}
