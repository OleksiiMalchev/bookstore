package com.javacourse.bookstore.services;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public int count(String echo) {
        return echo.length();
    }
}
