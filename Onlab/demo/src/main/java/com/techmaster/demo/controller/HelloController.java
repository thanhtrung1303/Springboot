package com.techmaster.demo.controller;

import java.util.Random;

import com.techmaster.model.Book;

import org.apache.logging.log4j.message.Message;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    String hello() {
        return "Hello World, Spring Boot!";
    }

    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book book() {
        return new Book("x111", "Dế Mèn Phiêu Lưu Ký", "Tô Hoài");
    }

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public Book book_xml() {
        return new Book("x111", "Dế Mèn Phiêu Lưu Ký", "Tô Hoài");
    }

    @GetMapping("/add/{a}/{b}")
    public int add(@PathVariable("a") int a, @PathVariable("b") int b) {
        return a + b;
    }

    @GetMapping("/name/{your_name}")
    public String hi(@PathVariable("your_name") String yourName) {
        return "Hi " + yourName;
    }

    @GetMapping("/add")
    public int add2(@RequestParam("a") int x, @RequestParam("b") int y) {
        return x + y;
    }

    @PostMapping("/hi")
    @ResponseBody
    public Message sayHi(@RequestBody Message message) {
        return message;
    }

}
