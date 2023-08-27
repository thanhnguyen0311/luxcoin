package com.thanhnguyen.luxcoin.service;

import com.thanhnguyen.luxcoin.model.dto.userdto.request.UserLoginDto;
import com.thanhnguyen.luxcoin.model.dto.userdto.request.UserPasswordEditRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.List;

public interface IUserService<User> {
    List<User> findAll();

    User findById(Long id);

    void save(User t);

    void remove(Long id);
    boolean existsUsername(String username);
    boolean existsEmail(String email);

    String login(UserLoginDto userLoginDto);
    User findByUsername(String username);

}
