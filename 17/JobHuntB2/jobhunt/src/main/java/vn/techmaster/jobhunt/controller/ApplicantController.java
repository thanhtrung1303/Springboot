package vn.techmaster.jobhunt.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.techmaster.jobhunt.model.Applicant;
import vn.techmaster.jobhunt.repository.ApplicantRepository;
import vn.techmaster.jobhunt.repository.EmployerRepository;
import vn.techmaster.jobhunt.repository.JobRepository;
import vn.techmaster.jobhunt.repository.SkillRepository;
import vn.techmaster.jobhunt.request.ApplicantRequest;
import vn.techmaster.jobhunt.service.ApplicantService;

@Controller
@RequestMapping("/applicant")
public class ApplicantController {
    @Autowired
    private ApplicantRepository applicantRepository;

    @Autowired
    private ApplicantService applicantService;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private SkillRepository skillRepository;



    @GetMapping("/list")
    public String applicantList(Model model) {
        model.addAttribute("applicants", applicantRepository.findAll());
        model.addAttribute("job", jobRepository.findAll());
        model.addAttribute("employer", employerRepository.findAll());
        model.addAttribute("applicantService", applicantService);
        return "applicant_list";
    }

    @GetMapping("/update/{id}")
    public String updateApplicant(Model model, @PathVariable String id) {
        Optional<Applicant> applicant = applicantRepository.findById(id);
        model.addAttribute("applicant", applicant);
        model.addAttribute("jobs", jobRepository.findAll());
        model.addAttribute("employerRepository", employerRepository);
        model.addAttribute("skills", skillRepository.findAll());
        return "applicant_update";
    }

    @PostMapping("/update/{id}")
    public String UpdateApplicant(@PathVariable String id, @ModelAttribute ApplicantRequest applicantRequest) {

        Applicant applicant = new Applicant(id, applicantRequest.job(), applicantRequest.name(),
                applicantRequest.email(), applicantRequest.phone(), applicantRequest.skills());
        applicantRepository.save(applicant);
        return "redirect:/applicant/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteApplicant(@PathVariable String id) {
        applicantRepository.deleteById(id);
        return "redirect:/applicant/list";
    }

    @GetMapping("/sendEmail/{id}")
    public String showForm(@PathVariable String id, Model model) {
        Optional<Applicant> applicant = applicantRepository.findById(id);
        model.addAttribute("applicant", applicant);
        return "send_mail";
    }

    @PostMapping("/sendEmail/{id}")
    public String sendMail(@RequestParam("to") String to,
            @RequestParam("subject") String subject, @RequestParam("content") String content) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(content);
        javaMailSender.send(msg);
        return "sendMail_result";
    }

}
