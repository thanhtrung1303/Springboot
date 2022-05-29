package vn.techmaster.job_hunt.repository;


import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import vn.techmaster.job_hunt.model.Employer;

@Repository
public class EmployerRepo {
    private ConcurrentHashMap<String, Employer> employers = new ConcurrentHashMap<>();
    
    public EmployerRepo() {
    }
    
    public Employer add(Employer employer) {
        String id = UUID.randomUUID().toString();
        employer.setId(id);
        employers.put(id, employer);
        return employer;
    }

    public Collection<Employer> getAll() {
        return employers.values();
    }

    public Employer findById(String id) {
        return employers.get(id);
    }
    
    @PostConstruct
    public void addSomeData() {
        this.add(Employer.builder()
                .name("fpt").website("http:fpt.com")
        .logo_path("fpt.png")
                .email("fpt@gmail.com").build());
    }
}
