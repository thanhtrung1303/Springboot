package vn.techmaster.jobhunt.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.jobhunt.model.Employer;
import vn.techmaster.jobhunt.repository.EmployerRepository;

@Service
public class EmployerService {
    @Autowired
    private EmployerRepository employerRepository;

    public Employer getEmployerById(String id) {
        Optional<Employer> optinalEmployer = employerRepository.findById(id);
        return optinalEmployer.get();
    }

    public Employer createEmployer(Employer employer) {
        String id = UUID.randomUUID().toString();
        employer.setId(id);
        employerRepository.save(employer);
        return employer;
    }

    public void updateLogo(String id, String logo_path) {
        var emp = employerRepository.getById(id);
        emp.setLogoPath(logo_path);
        employerRepository.save(emp);
    }

    public Employer updateEmployer(Employer employer) {
       return employerRepository.save(employer);
    }

    public void deleteEmployerById(String id) {
        employerRepository.deleteById(id);
    }

    public List<Employer> findByEmployer(String keyword) {
        List<Employer> employerList = employerRepository.findAll().stream()
                .filter(e -> e.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
        return employerList;
    }
}
