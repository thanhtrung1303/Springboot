package vn.techmaster.jobhunt.repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import vn.techmaster.jobhunt.model.Employer;

@Repository
public class EmployerRepository {
    ConcurrentHashMap<String, Employer> employers;

    public EmployerRepository() {
        employers = new ConcurrentHashMap<>();
        employers.put("employer1",
                new Employer("employer1", "FPT", "logo/fpt.png", "http://fpt.com.vn",
                        "fpt@gmail.com"));
        employers.put("employer2",
                new Employer("employer2", "VNG", "logo/vng.png", "http://vng.com.vn",
                        "vng@gmail.com"));
        employers.put("employer3",
                new Employer("employer3", "Viettel", "logo/viettel.png", "https://vietteltelecom.vn",
                        "viettel@gmail.com"));
    }

    public List<Employer> getEmployers() {
        return employers.values().stream().toList();
    }

    public Employer getEmployerById(String id) {
        return employers.get(id);
    }

    public void createEmployer(Employer employer) {
        employers.put(employer.getId(), employer);
    }

    public void updateEmployer(Employer employer) {
        employers.put(employer.getId(), employer);
    }

    public void deleteEmployerById(String id) {
        employers.remove(id);
    }
}
