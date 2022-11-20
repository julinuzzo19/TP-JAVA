package auth.springframework.controller;

import auth.springframework.config.IJwtGenerator;
import auth.springframework.dtos.LoginDTO;
import auth.springframework.dtos.RegisterDTO;
import auth.springframework.exception.UserNotFoundException;
import auth.springframework.model.User;
import auth.springframework.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private IUserService userService;
    private IJwtGenerator jwtGenerator;

    @Autowired
    public UserController(IUserService userService, IJwtGenerator jwtGenerator){
        this.userService=userService;
        this.jwtGenerator=jwtGenerator;
    }
    //swagger doc
    // http://localhost:8081/swagger-ui/index.html#/
    @PostMapping("/register")
    public ResponseEntity<?> postUser(@RequestBody RegisterDTO user){
        try{
            userService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO user) {
        try {
            if(user.getEmail() == null || user.getPassword() == null) {
                throw new UserNotFoundException("UserName or Password is Empty");
            }
            User userData = userService.getUserByEmailAndPassword(user.getEmail(), user.getPassword());
            if(userData == null){
                throw new UserNotFoundException("UserName or Password is Invalid");
            }
            return new ResponseEntity<>(jwtGenerator.generateToken(userData), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
