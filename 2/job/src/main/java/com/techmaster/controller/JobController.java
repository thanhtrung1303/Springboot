package com.techmaster.controller;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.techmaster.dto.JobRequest;
import com.techmaster.model.Job;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
public class JobController {
        private ConcurrentHashMap<String, Job> jobs;

        public JobController() {
                jobs = new ConcurrentHashMap<>();
                jobs.put("JB-01", new Job("JB-01", "Lập trình viên Front-End",
                                "HTML, CSS, và ngôn ngữ lập trình JavaScript, các framework như Bootstrap, Foundation, Backbone, AngularJS, và EmberJS",
                                "Ha Noi", 10000000, 50000000, "abc@gmail.com"));
                jobs.put("JB-02", new Job("JB-02", "Lập trình viên Back-End",
                                "PHP, Ruby, Python, Java, và .Net để xây dựng một ứng dụng, và các công cụ như MySQL, Oracle, và SQL Server",
                                "Da Nang", 10000000, 80000000, "abcd@gmail.com"));
                jobs.put("JB-03",
                                new Job("JB-03", "Lập trình viên Fullstack",
                                                "Đảm nhận công việc của fontend và backend",
                                                "Da nang", 20000000, 100000000, "abcdef@gmail.com"));
                jobs.put("JB-04", new Job("JB-04", "Lập trình viên Front-End",
                                "HTML, CSS, và ngôn ngữ lập trình JavaScript, các framework như Bootstrap, Foundation, Backbone, AngularJS, và EmberJS",
                                "Ho Chi Minh", 15000000, 60000000, "qwerty@gmail.com"));
        }

        @GetMapping()
        public List<Job> getAllJobs() {
                return jobs.values().stream().toList();
        }

        @PostMapping
        public Job createNewJob(@RequestBody JobRequest jobRequest) {
                String uuid = UUID.randomUUID().toString();
                Job newJob = new Job(uuid, jobRequest.title(), jobRequest.description(), jobRequest.location(),
                                jobRequest.min_salary(), jobRequest.max_salary(), jobRequest.email());
                jobs.put(uuid, newJob);
                return newJob;
        }

        @GetMapping(value = "/{id}")
        public Job searchById(@PathVariable("id") String id) {
                return jobs.get(id);
        }

        @PostMapping(value = "/{id}")
        public Job updateJobByID(@RequestParam("id") String id, @RequestBody JobRequest jobRequest) {
                Job updateJod = new Job(id, jobRequest.title(), jobRequest.description(), jobRequest.location(),
                                jobRequest.min_salary(), jobRequest.max_salary(), jobRequest.email());
                jobs.put(id, updateJod);
                return updateJod;
        }

        @DeleteMapping(value = "/{id}")
        public Job deleteById(@RequestParam("id") String id) {
                Job removeJob = jobs.remove(id);
                return removeJob;
        }

        @GetMapping("/location")
        public List<Job> sortByLocation() {
                return jobs.values().stream()
                                .sorted(((o1, o2) -> o2.getLocation().compareTo(o1.getLocation()))).toList();
        }

        @GetMapping(value = "/{salary}")
        public List<Job> findJobBySalary(@PathVariable("salary") int salary) {
                return jobs.values().stream().filter(o1 -> o1.getMax_salary() >= salary && o1.getMin_salary() <= salary)
                                .collect(Collectors.toList());
        }

        @GetMapping(value = "/{job}")
        public List<Job> findJobByKeyWord(@PathVariable("keyword") String keyword) {
                return jobs.values()
                                .stream()
                                .filter(o1 -> o1.getTitle().toLowerCase().contains(keyword.toLowerCase())
                                                || o1.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                                .collect(Collectors.toList());
        }

        @GetMapping(value = "/{jobByLocation}")
        public List<Job> searchJobByLocation(@PathVariable("keyword") String keyword,
                        @PathVariable("location") String location) {
                return jobs.values()
                                .stream()
                                .filter(o1 -> (o1.getTitle().toLowerCase().contains(keyword.toLowerCase())
                                                || o1.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                                                && o1.getLocation() == location)
                                .collect(Collectors.toList());
        }
}
