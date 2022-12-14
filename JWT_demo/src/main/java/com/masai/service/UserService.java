package com.masai.service;

import com.masai.exception.*;
import com.masai.model.*;

import java.util.*;

public interface UserService {

    public User registerUser(User user) throws CustomException;

    public User deleteUser(String username) throws CustomException;

    public List<User> findAll() throws CustomException;

    public User findByUsername(String username) throws CustomException;

}
