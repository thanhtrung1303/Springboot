package vn.techmaster.hellojpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.AllArgsConstructor;
import vn.techmaster.hellojpa.model.Employer;
import vn.techmaster.hellojpa.repository.EmployerRepo;

@RestController
@RequestMapping("/api/v1/employer")
@AllArgsConstructor
public class EmployerController {

    private final EmployerRepo employerRepo;

    @GetMapping()
    public List<Employer> getAll() {
        return employerRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Employer> getById(@PathVariable Long id) {
        return employerRepo.findById(id);
    }



}
