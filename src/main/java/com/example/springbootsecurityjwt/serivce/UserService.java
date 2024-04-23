package com.example.springbootsecurityjwt.serivce;

import com.example.springbootsecurityjwt.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    User save(User user);

    User create(User user);

    User getByUsername(String username);

    UserDetailsService userDetailsService();

    User getCurrentUser();

}
