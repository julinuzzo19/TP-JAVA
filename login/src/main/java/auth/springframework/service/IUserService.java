package auth.springframework.service;

import auth.springframework.dtos.RegisterDTO;
import auth.springframework.exception.UserNotFoundException;
import auth.springframework.model.User;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    public void saveUser(RegisterDTO user);
    public User getUserByEmailAndPassword(String email, String password) throws UserNotFoundException;
}
