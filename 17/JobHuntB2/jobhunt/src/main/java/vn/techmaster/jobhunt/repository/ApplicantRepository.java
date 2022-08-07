package vn.techmaster.jobhunt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.techmaster.jobhunt.model.Applicant;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, String> {
}
