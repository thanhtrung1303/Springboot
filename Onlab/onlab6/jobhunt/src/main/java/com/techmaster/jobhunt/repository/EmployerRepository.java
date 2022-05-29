package vn.techmaster.jobhunt.repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import vn.techmaster.jobhunt.model.Employer;

@Repository
public class EmployerRepository {
    private ConcurrentHashMap<String, Employer> employers = new ConcurrentHashMap<>();

    public EmployerReppository() {

    }

    // Cần sinh UUID ở đây
    public Employer add(Employer employer) {
        String id = UUID.randomUUID().toString();
        employer.setId(id);
        employers.put(id, employer);
        return employer;
    }

    public Collection<Employer> getALl() {
        return employers.values();
    }

    public Employer findId(String id) {
        return employers.get(id);
    }

    @PostConstruct
    public void addSomeDate() {
        this.add(Employer.builder().name(fpt).website("http:fpt.com").logo_path("fpt.png").email("fpt@gmail.com").build());
        this.add(Employer.builder().name(cmc).website("http:cmc.com").logo_path("fpt.png").email("cmc@gmail.com").build());
        this.add(Employer.builder().name(fpt).website("http:fpt.com").logo_path("fpt.png").email("fpt@gmail.com").build());
    }
}
