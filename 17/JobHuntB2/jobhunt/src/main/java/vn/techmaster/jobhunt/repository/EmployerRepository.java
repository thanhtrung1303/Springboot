package vn.techmaster.jobhunt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.jobhunt.model.Employer;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, String> {
}
