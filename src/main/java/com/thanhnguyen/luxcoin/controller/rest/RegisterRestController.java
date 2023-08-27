package com.thanhnguyen.luxcoin.controller.rest;

import com.thanhnguyen.luxcoin.model.entity.User;
import com.thanhnguyen.luxcoin.repository.IUserRepository;
import com.thanhnguyen.luxcoin.service.IUserService;
import com.thanhnguyen.luxcoin.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/register")
public class RegisterRestController {

    @Autowired
    private IUserService<User> userService;


    @GetMapping("/check-username/{username}")
    public ResponseEntity<?>  checkUsername(@PathVariable String username) {
        if(!userService.existsUsername(username)){
            return new ResponseEntity<>("body",HttpStatus.NOT_ACCEPTABLE);
        }
       return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/checkEmail")
    public ResponseEntity<String> checkEmail(@RequestBody String email) {
        if(!userService.existsEmail(email))
            return new ResponseEntity<>("body",HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
