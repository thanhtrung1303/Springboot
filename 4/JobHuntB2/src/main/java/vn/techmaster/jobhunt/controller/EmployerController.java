package vn.techmaster.jobhunt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.techmaster.jobhunt.model.Employer;
import vn.techmaster.jobhunt.repository.EmployerRepository;
import vn.techmaster.jobhunt.request.EmployerRequest;
import vn.techmaster.jobhunt.service.StorageService;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/employer")
public class EmployerController {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private StorageService storageService;

    @GetMapping("/list")
    public String employerList(Model model) {
        model.addAttribute("employers", employerRepository.getEmployers());
        return "employer_list";
    }

    @GetMapping("/add")
    public String addEmployerForm(Model model) {
        model.addAttribute("employer", new EmployerRequest("", "", "", "", null));
        return "employer_add";
    }

    @PostMapping(value = "/add", consumes = { "multipart/form-data" })
    public String addEmployer(@Valid @ModelAttribute("employer") EmployerRequest employerRequest,
                              BindingResult result,
                              Model model) {
        if (employerRequest.logo().getOriginalFilename().isEmpty()) {
            result.addError(new FieldError("employer", "logo", "Logo is required"));
        }

        if (result.hasErrors()) {
            return "employer_add";
        }

        Employer emp = employerRepository.createEmployer(Employer.builder()
                .name(employerRequest.name())
                .website(employerRequest.website())
                .email(employerRequest.email())
                .build());

        try {
            String logoFileName = storageService.saveFile(employerRequest.logo(), emp.getId());
            employerRepository.updateLogo(emp.getId(), logoFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/employer/list";
    }

    @GetMapping("/update/{id}")
    public String updateEmployer(Model model, @PathVariable String id) {
        Employer employer = employerRepository.getEmployerById(id);
        model.addAttribute("employer", employer);
        return "employer_update";
    }

    @PostMapping("/update/{id}")
    public String updateEmployer(@PathVariable String id, @ModelAttribute EmployerRequest employerRequest) {
        Employer employer = new Employer(id, employerRequest.name(),
                employerRepository.getEmployerById(id).getLogo_path(),
                employerRequest.website(), employerRequest.email());

        employerRepository.updateEmployer(employer);
        return "redirect:/employer/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployer(@PathVariable String id) {
        employerRepository.deleteEmployerById(id);
        return "redirect:/employer/list";
    }
}
