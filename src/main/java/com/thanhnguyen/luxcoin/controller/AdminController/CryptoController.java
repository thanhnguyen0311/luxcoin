package com.thanhnguyen.luxcoin.controller.AdminController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/cryptos")
public class CryptoController {
    @GetMapping("/cryptos")
    public ModelAndView cryptos(Model model){
        return new ModelAndView("admin/listcryptos");
    }
}
