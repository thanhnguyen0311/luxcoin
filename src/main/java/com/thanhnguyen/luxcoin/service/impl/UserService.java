package com.thanhnguyen.luxcoin.service.impl;

import com.thanhnguyen.luxcoin.configuration.JwtUtils;
import com.thanhnguyen.luxcoin.model.dto.userdto.request.UserPasswordEditRequest;
import com.thanhnguyen.luxcoin.model.entity.User;
import com.thanhnguyen.luxcoin.model.dto.userdto.request.UserLoginDto;
import com.thanhnguyen.luxcoin.repository.IUserRepository;
import com.thanhnguyen.luxcoin.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements IUserService<User>, Validator {

    private final IUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;


    @Override
    public boolean existsUsername(String username){
        Optional<User> u = this.userRepository.findByUsername(username);
        return u.isEmpty();
    }
    @Override
    public boolean existsEmail(String email){
        Optional<User> u = this.userRepository.findByEmail(email);
        return u.isEmpty();
    }
    @Override
    public void save(User user){
        this.userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public void remove(Long id) {
    }

    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserLoginDto userLoginDto = (UserLoginDto) target;
        String username = userLoginDto.getUsername();
        String password = this.passwordEncoder.encode(userLoginDto.getPassword());
        User user = this.userRepository.findByUsername(username).orElse(null);
        if(user != null){
            if(user.getUsername().equals(username) || user.getEmail().equals(username)){
                if(user.getPassword().equals(password)){
                    return;
                };
                errors.rejectValue("username","username.login","Thông tin đăng nhập không hợp lệ");
                return;
            }
            errors.rejectValue("username","username.login","Thông tin đăng nhập không hợp lệ");
        }
        errors.rejectValue("username","username.login","Thông tin đăng nhập không hợp lệ");
    }

    @Override
    public String login(UserLoginDto userLoginDto) {
        User user = userRepository.findByUsername(userLoginDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if(!passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())){
            throw new AuthenticationServiceException("Wrong password");
        }
        return jwtUtils.generateToken((UserDetails) user);
    }


    @Override
    public User findByUsername(String username){
        return this.userRepository.findByUsername(username).orElse(null);
    }
}
