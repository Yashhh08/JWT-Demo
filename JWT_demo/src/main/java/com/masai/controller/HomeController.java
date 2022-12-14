package com.masai.controller;

import com.masai.exception.*;
import com.masai.model.*;
import com.masai.security.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String home(@RequestHeader String Authorization){
        System.out.println(Authorization);
        return "Welcome to JWT Demo Application";
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{

        try
            {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword())
            );
        }
        catch (Exception e)
        {
            throw new Exception("Invalid username/password");
        }

        return jwtUtil.generateToken(jwtRequest.getUsername());

    }


}
