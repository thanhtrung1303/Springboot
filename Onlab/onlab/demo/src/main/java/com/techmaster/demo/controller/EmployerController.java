package com.techmaster.demo.controller;

import com.techmaster.demo.model.Employer;
import com.techmaster.demo.repository.EmployerRepository;
import com.techmaster.demo.request.EmployerRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employer")
public class EmployerController {
    @Autowired
    private EmployerRepository employerRepository;

    @GetMapping
    public String listAllEmployers(Model model) {
        model.addAttribute("employers", employerRepository.getALl());
        return "employers";
    }

    @GetMapping(value = "/{id}")
    public String showEmployerDetail(Model model, @PathVariable String id) {
        model.addAttribute("employer", employerRepository.findById(id));
        return "employer";
    }

    @GetMapping("/add")
    public String addEmployerForm(Model model) {
        model.addAttribute("employer", new EmployerRequest("","","","",null));
        return "employer_add";
    }

    @PostMapping(value= "/add", consumes ={"multipart/form-data"})
    public String addEmployer(Model model, @ModelAttribute EmployerRequest employerRequest, BindingResult result
    ) {
        if (result.hasErrors()) {
            return "employer_add";
        }
        System.out.println(employerRequest.logo().getOriginalFilename());
        return "redirect:/employers";
    }
}