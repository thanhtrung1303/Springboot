package com.techmaster.demo.repository;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import com.techmaster.demo.model.Employer;

import org.springframework.stereotype.Repository;

@Repository
public class EmployerRepository {
    private ConcurrentHashMap<String, Employer> employers = new ConcurrentHashMap<>();

    public EmployerRepository() {

    }

    public Employer add(Employer employer) {
        String id = UUID.randomUUID().toString();
        employer.setId(id);
        employers.put(id, employer);
        return employer;
    }

    public Collection<Employer> getALl() {
        return employers.values();
    }

    public Employer findById(String id) {
        return employers.get(id);
    }

    @PostConstruct
    public void addSomeDate() {
        this.add(Employer.builder().name("FPT").website("http://fpt.com.vn").logo_path("fpt.png").email("fpt@gmail.com")
                .build());
        this.add(Employer.builder().name("Viettel").website("http://viettel.com").logo_path("viettel.jpg").email("cmc@gmail.com")
                .build());
        this.add(Employer.builder().name("VNG").website("http://vng.com").logo_path("vng.jpg").email("fpt@gmail.com")
                .build());
    }
}
