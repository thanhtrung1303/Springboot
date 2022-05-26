package vn.techmaster.jobhunt.repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import vn.techmaster.jobhunt.model.Applicant;
import vn.techmaster.jobhunt.model.Skill;

@Repository
public class ApplicantRepository {
    ConcurrentHashMap<String, Applicant> applicants;

    public ApplicantRepository() {
        applicants = new ConcurrentHashMap<>();
        applicants.put("app1", new Applicant("app1", "jb1", "Nguyễn Văn A", "nva@mail.com", "123456789",
                List.of(Skill.CSharp, Skill.SQL)));
        applicants.put("app2", new Applicant("app2", "jb2", "Nguyễn thị B", "ntb@mail.com", "223452129",
                List.of(Skill.Java, Skill.CSharp)));
        applicants.put("app3", new Applicant("app3", "jb3", "Nguyễn Văn C", "nvc@mail.com", "323167892",
                List.of(Skill.CSharp, Skill.SQL, Skill.AWS)));
        applicants.put("app3", new Applicant("app3", "jb3", "Nguyễn thị D", "ntd@mail.com", "323167892",
                List.of(Skill.CSharp, Skill.SQL, Skill.AWS, Skill.Java)));
    }

    public List<Applicant> getApplicants() {
        return applicants.values().stream().toList();
    }

    public Applicant getApplicantById(String id) {
        return applicants.get(id);
    }

    public void createApplicant(Applicant applicant) {
        applicants.put(applicant.getId(), applicant);
    }

    public void updateApplicant(Applicant applicant) {
        applicants.put(applicant.getId(), applicant);
    }

    public void deleteApplicantById(String id) {
        applicants.remove(id);
    }
}
