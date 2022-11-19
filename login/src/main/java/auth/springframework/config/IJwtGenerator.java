package auth.springframework.config;

import auth.springframework.model.User;

import java.util.Map;

public interface IJwtGenerator {

    Map<String, String> generateToken(User user);
}
