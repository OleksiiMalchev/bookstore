package com.javacourse.bookstore;

import com.javacourse.bookstore.services.SomeСlass;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookstoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);

	}
	@Bean
	public SomeСlass someСlass(){
		return new SomeСlass();
	}

}
