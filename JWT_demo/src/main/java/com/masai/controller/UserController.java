package com.masai.controller;

import com.masai.exception.*;
import com.masai.model.*;
import com.masai.security.*;
import com.masai.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.*;
import javax.servlet.http.*;
import javax.validation.*;
import java.util.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(value = "/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) throws CustomException{

        User registered = userService.registerUser(user);

        return new ResponseEntity<>(registered,HttpStatus.CREATED);

    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll() throws CustomException{

        List<User> users = userService.findAll();

        return new ResponseEntity<>(users,HttpStatus.OK);

    }

    @DeleteMapping("/user/{username}")
    public ResponseEntity<User> deleteUser(@PathVariable("username") String username) throws CustomException{

        User deleted = userService.deleteUser(username);

        return new ResponseEntity<>(deleted,HttpStatus.OK);

    }

    @GetMapping("/user/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable("username") String username) throws CustomException{

        User user = userService.findByUsername(username);

        return new ResponseEntity<>(user,HttpStatus.OK);

    }

    @GetMapping("/user")
    public ResponseEntity<User> findByUserJWT(@RequestHeader String Authorization) throws CustomException{

        String auth = Authorization;

        String token = Authorization.substring(7);

        String username = jwtUtil.extractUsername(token);

        User user = userService.findByUsername(username);

        return new ResponseEntity<>(user, HttpStatus.OK);

    }

}
