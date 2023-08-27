package com.thanhnguyen.luxcoin.controller.AdminController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class AdminHomeController {
    @GetMapping("/admin")
    public ModelAndView home(Model model){
        return new ModelAndView("admin/home");
    }
    @GetMapping("/cryptos")
    public ModelAndView cryptos(){
        return new ModelAndView("admin/listcryptos");
    }
}
