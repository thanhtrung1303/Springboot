package vn.techmaster.jobhunt.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.jobhunt.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, String>{}
