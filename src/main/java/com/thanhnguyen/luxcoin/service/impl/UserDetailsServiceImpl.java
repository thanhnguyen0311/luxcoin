package com.thanhnguyen.luxcoin.service.impl;

import com.thanhnguyen.luxcoin.model.entity.User;
import com.thanhnguyen.luxcoin.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElse(null);
        if(user == null){
            throw new UsernameNotFoundException("User " + username + "was not found in database!");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword()
                , Collections.singleton(new SimpleGrantedAuthority("ROLE_"+user.getRole().getTitle())));
    }
}
