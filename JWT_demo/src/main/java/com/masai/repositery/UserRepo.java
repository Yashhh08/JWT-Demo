package com.masai.repositery;

import com.masai.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    public Optional<User> findByUsername(String username);

}
