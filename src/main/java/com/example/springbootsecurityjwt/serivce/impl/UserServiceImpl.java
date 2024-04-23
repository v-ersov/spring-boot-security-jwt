package com.example.springbootsecurityjwt.serivce.impl;

import com.example.springbootsecurityjwt.model.User;
import com.example.springbootsecurityjwt.repository.UserRepository;
import com.example.springbootsecurityjwt.serivce.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User create(User user) {
        if (repository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("A user with the same name already exists");
        }

        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("A user with this email already exists");
        }

        return save(user);
    }

    @Override
    public User getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User is not found"));

    }

    @Override
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    @Override
    public User getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }
}
