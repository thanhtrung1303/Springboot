package vn.techmaster.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {
    @GetMapping()
    public String showPage(){
        return "index";
    }

    @GetMapping("/login")
    public String showLogin(){
        return "login";
    }
    @GetMapping("/logout")
    public String showlogout(){
        return "logout";
    }

    @GetMapping("/register")
    public String showRegisterPage(){
        return "register";
    }
}
