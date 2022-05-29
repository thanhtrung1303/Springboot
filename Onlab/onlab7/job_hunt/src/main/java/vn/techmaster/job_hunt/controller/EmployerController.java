package vn.techmaster.job_hunt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmaster.job_hunt.repository.EmployerRepo;

@Controller
@RequestMapping("/employer")
public class EmployerController {
    @Autowired private EmployerRepo repo;
    @GetMapping
    public String listAllEmployers(Model model) {
        model.addAttribute("employers", repo.getAll());
        return "employers";
    }

    

}
