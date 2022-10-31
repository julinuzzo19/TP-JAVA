package com.api.controller;

import com.api.dto.CreateUserRequest;
import com.api.dto.LoginRequest;
import com.api.dto.LoginResponse;
import com.api.model.User;
import com.api.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value="login", description="Operations pertaining to login")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping(path="/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return userService.login(loginRequest);
    }

    @ApiOperation(value = "create user",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 406, message = "You are not able to create user")})
    @PostMapping(path = "/registration")
    public ResponseEntity<Void> createNewUser(@RequestBody CreateUserRequest request) {
        User userExists = userService.findUserByEmail(request.getEmail());
        if (userExists != null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            userService.saveUser(request);
            return new ResponseEntity<Void>(HttpStatus.CREATED);

        }
    }

//    @Autowired
//    private LibrosService librosService;
//    @RequestMapping(value="/buscarLibros/{textoPrueba}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
////    @GetMapping(path="/buscarLibros/{textoPrueba}")
//    public BusquedaLibroResponse registration(@PathVariable String textoPrueba)throws IOException {
//        BusquedaLibroResponse prueba = librosService.buscadorLibro(textoPrueba);
//        System.out.println(prueba);
//        return prueba;
//    }

}
