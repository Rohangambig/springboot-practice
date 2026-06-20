package com.entire.demo.service;

import com.entire.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomDetailService implements UserDetailsService {

    @Autowired
    UserRepository user_repos;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return user_repos.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("Username not found"));
    }
}
