package com.masai.service;

import com.masai.exception.*;
import com.masai.model.*;
import com.masai.repositery.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) throws CustomException {

        Optional<User> exist = userRepo.findByUsername(user.getUsername());

        if(exist.isPresent())
        {
            throw new CustomException("User already exist with username "+user.getUsername().toUpperCase());
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRole("ROLE_"+user.getRole().toUpperCase());

        return userRepo.save(user);

    }

    @Override
    public User deleteUser(String username) throws CustomException {

        Optional<User> exist = userRepo.findByUsername(username);

        if(exist.isPresent())
        {
            userRepo.delete(exist.get());

            return exist.get();
        }
        else
        {
            throw new CustomException("No user found with username "+username.toUpperCase());
        }

    }

    @Override
    public List<User> findAll() throws CustomException {

        List<User> users = userRepo.findAll();

        if(users.isEmpty())
        {
            throw new CustomException("No user found");
        }
        else
        {
            return users;
        }

    }

    @Override
    public User findByUsername(String username) throws CustomException {

        User user = userRepo.findByUsername(username).orElseThrow(()->new CustomException("No user found with username "+username.toUpperCase()));

        return user;

    }
}
