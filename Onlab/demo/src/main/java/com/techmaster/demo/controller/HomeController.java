package com.techmaster.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class HomeController {
    @RequestMapping("/")
    public String HomePage() {
        return "index";
    }
}
