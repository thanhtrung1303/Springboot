package com.example.onlab25.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/admin/blogs")
    public String getBlogHome() {
        return "blog";
    }

    @GetMapping("/admin/users")
    public String getUserHome() {
        return "user";
    }
}
