package com.masai.security;

import com.masai.model.User;
import com.masai.repositery.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("No user found with username "+username));

        return new CustomUserDetails(user);

    }



}
