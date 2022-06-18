package vn.techmaster.userbackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.AllArgsConstructor;
import vn.techmaster.userbackend.service.UserService;

@Controller
@AllArgsConstructor
public class UserViewController {
    private final UserService userService;

    @GetMapping("/")
    public String getUsersPage(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "index";
    }

    @GetMapping("/create")
    public String getCreatePage() {
        return "create";
    }

    @GetMapping("/detail/{id}")
    public String getDetailPage(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("images", userService.getFiles(id));
        return "detail";
    }
}
