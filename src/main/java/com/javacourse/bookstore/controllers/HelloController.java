package com.javacourse.bookstore.controllers;
import com.javacourse.bookstore.services.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final HelloService seviceHello;
    @Autowired
    public HelloController(HelloService seviceHello) {
        this.seviceHello = seviceHello;
    }
    @GetMapping("/hello")
    public int helloWorld(@RequestParam("echo") String echo) {
        return seviceHello.count(echo);
    }
}