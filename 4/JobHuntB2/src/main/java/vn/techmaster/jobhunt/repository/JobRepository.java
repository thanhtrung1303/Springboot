package vn.techmaster.jobhunt.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import vn.techmaster.jobhunt.model.City;
import vn.techmaster.jobhunt.model.Job;

@Repository
public class JobRepository {
    
    private ConcurrentHashMap<String, Job> jobs;

    public JobRepository() {
        jobs = new ConcurrentHashMap<>();
        jobs.put("jb1",
                new Job("jb1", "employer1", "Web-design", "lập trình font-end",
                        City.HaiPhong, LocalDateTime.now(), LocalDateTime.now()));
        jobs.put("jb2",
                new Job("jb2", "employer2", "Java-programing", "lập trình back-end",
                        City.HaNoi, LocalDateTime.now(), LocalDateTime.now()));
        jobs.put("jb3",
                new Job("jb3", "employer3", "Fullstack", "lập trình fullstack",
                        City.HaiPhong, LocalDateTime.now(), LocalDateTime.now()));

    }

    public List<Job> listjob() {
        return jobs.values().stream().toList();
    }

    public Job getById(String id) {
        return jobs.get(id);
    }

    public void addJob(Job job) {
        jobs.put(job.getId(), job);
    }

    public void deleteJobById(String id) {
        jobs.remove(id);
    }

    public void updateJob(Job job) {
        jobs.put(job.getId(), job);
    }
}
