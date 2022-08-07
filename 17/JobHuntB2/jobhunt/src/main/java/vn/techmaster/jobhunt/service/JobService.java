package vn.techmaster.jobhunt.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.jobhunt.model.Job;
import vn.techmaster.jobhunt.repository.JobRepository;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    public Job getById(String id) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        return optionalJob.get();
    }

    public void addJob(Job job) {
        jobRepository.save(job);
    }

    public void deleteJobById(String id) {
        jobRepository.deleteById(id);
    }

    public void updateJob(Job job) {
        jobRepository.save(job);
    }

    public List<Job> findByKeyword(String keyword) {
        List<Job> jobList = jobRepository.findAll().stream()
                .filter(j -> j.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
        return jobList;
    }
}
