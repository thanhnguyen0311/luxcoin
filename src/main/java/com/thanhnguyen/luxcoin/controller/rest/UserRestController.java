package com.thanhnguyen.luxcoin.controller.rest;

import com.thanhnguyen.luxcoin.configuration.JwtUtils;
import com.thanhnguyen.luxcoin.model.dto.userdto.request.UserEditRequestDto;
import com.thanhnguyen.luxcoin.model.dto.userdto.request.UserPasswordEditRequest;
import com.thanhnguyen.luxcoin.model.dto.userdto.response.UserEditRespone;
import com.thanhnguyen.luxcoin.model.dto.userdto.response.UserInfoResponse;
import com.thanhnguyen.luxcoin.model.entity.User;
import com.thanhnguyen.luxcoin.model.mapper.IUserMapper;
import com.thanhnguyen.luxcoin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/user")
public class UserRestController {

    @Autowired
    private IUserService<User> userService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<?> getUserInfo(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String token = null;
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("luxToken")) {
                    token = cookie.getValue();
                }
            }
        }
        if(token != null){
            UserInfoResponse userInfoResponse = IUserMapper.INSTANCE.userInfoResponse(userService.findByUsername(jwtUtils.extractUsername(token)));
            return ResponseEntity.ok(userInfoResponse);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("not found");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserInfoSettings( @PathVariable String id){
        UserEditRespone userEditRespone = IUserMapper.INSTANCE.userEditResponse(userService.findById(Long.parseLong(id)));
        if(userEditRespone == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(userEditRespone);
    }
    @PatchMapping ("/edit/{id}")
    public ResponseEntity<?> editUser(@PathVariable String id,@RequestParam("name") String name
            , @RequestParam("email") String email, @RequestParam("phone") String phone, @RequestParam("description") String description ,@RequestParam("image") MultipartFile image){

        User user = userService.findById(Long.valueOf(id));
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        }
        if(!userService.existsEmail(email) && !email.equals(user.getEmail())){
            return new ResponseEntity<>("body",HttpStatus.NOT_ACCEPTABLE);
        }
        try {
            IUserMapper.INSTANCE.editRequestToEntity(new UserEditRequestDto(name,email,phone,description,image.getBytes()), user);
            userService.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Failed to upload image");
        }
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> editUser(@RequestHeader("Authorization") String authorizationHeader,  @RequestBody UserPasswordEditRequest userPasswordEditRequest) {
        User user = userService.findByUsername(jwtUtils.extractUsername(authorizationHeader.substring(7)));
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        }
        if(user.getPassword().equals(passwordEncoder.encode(userPasswordEditRequest.getPassword()))){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Password invalid");
        }
        if(userPasswordEditRequest.getNewPassword().length()<8){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Password too short");
        }
        user.setPassword(passwordEncoder.encode(userPasswordEditRequest.getNewPassword()));
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
