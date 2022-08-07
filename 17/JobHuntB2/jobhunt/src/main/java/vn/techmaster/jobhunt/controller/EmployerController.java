package vn.techmaster.jobhunt.controller;

import org.apache.naming.factory.LookupFactory;
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
import org.springframework.web.bind.annotation.RequestParam;

import vn.techmaster.jobhunt.model.Employer;
import vn.techmaster.jobhunt.repository.EmployerRepository;
import vn.techmaster.jobhunt.request.EmployerRequest;
import vn.techmaster.jobhunt.service.EmployerService;
import vn.techmaster.jobhunt.service.StorageService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/employer")
public class EmployerController {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private EmployerService employerService;

    @Autowired
    private StorageService storageService;

    @GetMapping("/list")
    public String employerList(Model model, String keyword) {

        if (keyword != null) {
            model.addAttribute("employers", employerService.findByEmployer(keyword));
        } else {
            // model.addAttribute("employers", employerRepository.getEmployers());
            model.addAttribute("employers", employerRepository.findAll());
        }
        return "employer_list";
    }

    @GetMapping("/add")
    public String addEmployerForm(Model model) {
        model.addAttribute("employer", new EmployerRequest("", "", "", "", null));
        return "employer_add";
    }

    @PostMapping(value = "/add", consumes = { "multipart/form-data" })
    public String addEmployer(@Valid @ModelAttribute("employer") EmployerRequest employerRequest,
            BindingResult result, Model model) {
        if (employerRequest.logo().getOriginalFilename().isEmpty()) {
            result.addError(new FieldError("employer", "logo", "Logo is required"));
        }

        if (result.hasErrors()) {
            return "employer_add";
        }

        Employer employer = employerService.createEmployer(Employer.builder()
                .name(employerRequest.name())
                .website(employerRequest.website())
                .email(employerRequest.email())
                .build());

        try {
            String logoFileName = storageService.saveFile(employerRequest.logo(),
                    employer.getId());
            employerService.updateLogo(employer.getId(), logoFileName);
        } catch (IOException e) {
            employerService.deleteEmployerById(employer.getId());
            e.printStackTrace();
            return "employer_add";
        }

        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String updateEmployer(Model model, @PathVariable String id) {
        Employer employer = employerService.getEmployerById(id);
        model.addAttribute("employer", employer);
        return "employer_update";
    }

    @PostMapping(value = "/update/{id}", consumes = { "multipart/form-data" })
    public String updateEmployer(@PathVariable String id,
            @Valid @ModelAttribute("employer") EmployerRequest employerRequest) {

        Employer employer = new Employer(id, employerRequest.name(),
                employerService.getEmployerById(id).getLogoPath(),
                employerRequest.website(),
                employerRequest.email());

        employerService.updateEmployer(employer);

        return "redirect:/employer/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployer(@PathVariable String id) {
        Employer employer = employerRepository.getById(id);
        employerService.deleteEmployerById(id);
        storageService.deleteFile(employer.getLogoPath());
        return "redirect:/employer/list";
    }
}
