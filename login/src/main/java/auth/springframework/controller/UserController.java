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
@RequestMapping("/auth")
public class UserController {
    private IUserService userService;
    private IJwtGenerator jwtGenerator;

    @Autowired
    public UserController(IUserService userService, IJwtGenerator jwtGenerator){
        this.userService=userService;
        this.jwtGenerator=jwtGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> postUser(@RequestBody RegisterDTO user){
        try{
            userService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody LoginDTO user) {
        try {
            if(user.getEmail() == null || user.getPassword() == null) {
                throw new UserNotFoundException("No se ha encontrado el email o contraseña");
            }
            User userData = userService.getUserByEmailAndPassword(user.getEmail(), user.getPassword());
            if(userData == null){
                throw new UserNotFoundException("Credenciales incorrectas");
            }
            return new ResponseEntity<>(jwtGenerator.generateToken(userData), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
