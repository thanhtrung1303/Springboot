package vn.techmaster.jobhunt.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmaster.jobhunt.model.Applicant;
import vn.techmaster.jobhunt.repository.ApplicantRepository;
import vn.techmaster.jobhunt.repository.EmployerRepository;
import vn.techmaster.jobhunt.repository.JobRepository;
import vn.techmaster.jobhunt.request.ApplicantRequest;

@Controller
@RequestMapping("/applicant")
public class ApplicantController {
    @Autowired
    private ApplicantRepository applicantRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private EmployerRepository employerRepository;

    @GetMapping("/list")
    public String applicantList(Model model) {
        model.addAttribute("applicants", applicantRepository.getApplicants());
        model.addAttribute("jobRepository", jobRepository);
        model.addAttribute("employerRepository", employerRepository);
        return "applicant_list";
    }

    @GetMapping("/add/{id}")
    public String addApplicant(Model model, @PathVariable String id) {
        model.addAttribute("applicant", new Applicant());
        model.addAttribute("jobs", jobRepository.listjob());
        model.addAttribute("employerRepository", employerRepository);
        return "applicant_add";
    }

    @PostMapping("/add/{id}")
    public String AddApplicant(@ModelAttribute ApplicantRequest applicantRequest) {
        String uuid = UUID.randomUUID().toString();
        Applicant applicant = new Applicant(uuid, applicantRequest.job_id(), applicantRequest.name(),
                applicantRequest.email(), applicantRequest.phone(), applicantRequest.skills());
        applicantRepository.createApplicant(applicant);
        return "redirect:/applicant/list";
    }

    @GetMapping("/update/{id}")
    public String updateApplicant(Model model, @PathVariable String id) {
        Applicant applicant = applicantRepository.getApplicantById(id);
        model.addAttribute("applicant", applicant);
        model.addAttribute("jobs", jobRepository.listjob());
        model.addAttribute("employerRepository", employerRepository);
        return "applicant_update";
    }

    @PostMapping("/update/{id}")
    public String UpdateApplicant(@PathVariable String id, @ModelAttribute ApplicantRequest applicantRequest) {
        Applicant applicant = new Applicant(id, applicantRequest.job_id(), applicantRequest.name(),
                applicantRequest.email(), applicantRequest.phone(), applicantRequest.skills());
        applicantRepository.updateApplicant(applicant);
        return "redirect:/applicant/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteApplicant(@PathVariable String id) {
        applicantRepository.deleteApplicantById(id);
        return "redirect:/applicant/list";
    }
}
