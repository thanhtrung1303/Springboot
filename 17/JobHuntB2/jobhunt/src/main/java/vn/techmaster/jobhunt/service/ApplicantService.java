package vn.techmaster.jobhunt.service;

import org.springframework.stereotype.Service;
import vn.techmaster.jobhunt.model.Applicant;
import vn.techmaster.jobhunt.model.Skill;


@Service
public class ApplicantService {

    public String showSkill(Applicant applicant){
        return String.join(", ", applicant.getSkills().stream().map(Skill::getName).toList());
    }
}
