package com.thanhnguyen.luxcoin.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class WelcomeController {

    @GetMapping("/")
    public ModelAndView index(HttpServletRequest  request ){
        request.getHeader("Authorization");
        return new ModelAndView("/index");
    }
    @GetMapping("/not-found")
    public ModelAndView notFound() {
        return new ModelAndView("/error404");
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
