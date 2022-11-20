package auth.springframework.service;

import auth.springframework.dtos.RegisterDTO;
import auth.springframework.exception.UserNotFoundException;
import auth.springframework.model.User;
import auth.springframework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService implements IUserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public void saveUser(RegisterDTO userDTO) {
        User newUser = new User();
        newUser.setEmail(userDTO.getEmail());
        newUser.setName(userDTO.getName());
        newUser.setPassword(userDTO.getPassword());
        newUser.setLastName(userDTO.getLastName());
        newUser.setActive(1);

        userRepository.save(newUser);
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) throws UserNotFoundException {
        User user = userRepository.findByEmailAndPassword(email, password);
        if(user == null){
            throw new UserNotFoundException("Invalid id and password");
        }
        return user;
    }
}
