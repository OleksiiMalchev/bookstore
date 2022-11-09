package com.javacourse.bookstore;

import com.javacourse.bookstore.repositories.AuthorRealRepository;
import com.javacourse.bookstore.services.SomeСlass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.StreamSupport;

@Slf4j
@SpringBootApplication
public class BookstoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);

	}
	@Bean
	public SomeСlass someСlass(){
		return new SomeСlass();
	}
	@Bean
	public CommandLineRunner commandLineRunner(@Autowired AuthorRealRepository authorRealRepository){
		return t->{
			StreamSupport.stream(authorRealRepository.findAll().spliterator(), false).map(String::valueOf).forEach(log::info);
		};
	}

}
