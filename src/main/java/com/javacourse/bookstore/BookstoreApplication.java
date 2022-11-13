package com.javacourse.bookstore;

import com.javacourse.bookstore.domain.AuthorEntity;
import com.javacourse.bookstore.repositories.AuthorRepository;
import com.javacourse.bookstore.services.SomeСlass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
	public CommandLineRunner commandLineRunner(@Autowired AuthorRepository authorRepository){
		return t->{
//			StreamSupport.stream(authorRepository.findAll().spliterator(), false)
//					.map(String::valueOf).forEach(log::info);
			AuthorEntity john = authorRepository.findAuthor("John");
			log.info(String.valueOf(john));

		};
	}

}
