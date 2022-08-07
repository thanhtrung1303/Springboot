package vn.techmaster.jobhunt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.jobhunt.model.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, String> {
}