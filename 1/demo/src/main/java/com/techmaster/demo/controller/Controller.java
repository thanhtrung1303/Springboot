package com.techmaster.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.techmaster.demo.ulti.RandomStringUtils;
import com.techmaster.model.Student;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping("/random")
    public String randomString() {
        return RandomStringUtils.randomAlphaNumeric(8);
    }

    @RequestMapping("/qoute")
    public String randomQuote() {
        String[] qoute = new String[4];

        qoute[0] = "Kiến tha lâu đầy tổ";
        qoute[1] = "Có công mài sắt, có ngày nên kim";
        qoute[2] = "Không thầy đố mày làm nên";
        qoute[3] = "Học thầy không tày học bạn";

        Random rd = new Random();
        int rdIdx = rd.nextInt(4);
        return qoute[rdIdx];
    }

    @PostMapping("/bmi")
    public Double bmiIndex(@RequestParam double weight, @RequestParam double height) {
        return weight / (height * height);
    }

    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Student> student() {
        List<Student> students = new ArrayList<Student>();
        students.add(new Student(1, "A", 25));
        students.add(new Student(2, "B", 28));

        return students;
    }

    @PostMapping(value = "/student", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> studentList(@RequestParam int id, String name, @RequestParam int age) {
        List<Student> students = new ArrayList<Student>();
        students.add(new Student(id, name, age));
        return students;
    }
}
