package com.thanhnguyen.luxcoin.controller;

import com.thanhnguyen.luxcoin.model.dto.userdto.request.UserRegisterDto;
import com.thanhnguyen.luxcoin.model.entity.User;
import com.thanhnguyen.luxcoin.model.mapper.IUserMapper;
import com.thanhnguyen.luxcoin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private IUserService<User> userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView("/register");
        modelAndView.addObject("userRegisterDto",
                new UserRegisterDto());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView checkRegistration(@Validated @ModelAttribute("userRegisterDto") UserRegisterDto userRegisterDto,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/register");
        }
        userRegisterDto.update();
        userRegisterDto.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        userService.save(IUserMapper.INSTANCE.userRegisterDtoToEntity(userRegisterDto));
        ModelAndView modelAndView = new ModelAndView("/register-success");
        modelAndView.addObject("user",userRegisterDto);
        return modelAndView;
    }
}
