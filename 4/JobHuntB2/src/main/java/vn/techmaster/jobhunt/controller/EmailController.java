package vn.techmaster.jobhunt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmailController {
    @Autowired
    private JavaMailSender javaMailSender;

    @RequestMapping("applicant/mail")
    public String showForm() {
        return "send_mail";
    }
    @RequestMapping("applicant/send")
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
