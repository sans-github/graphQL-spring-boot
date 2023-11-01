package com.san;

import com.san.model.Author;
import com.san.model.Book;
import com.san.repository.AuthorRepository;
import com.san.repository.BookRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringBookGraphQLApp {
  public static void main(String[] args) {
    SpringApplication.run(SpringBookGraphQLApp.class, args);
  }

  @Bean
  ApplicationRunner applicationRunner(AuthorRepository authorRepository, BookRepository bookRepository) {
    return args -> {
      Author josh = authorRepository.save(new Author("Josh"));
      Author mark = authorRepository.save(new Author("Mark"));
      bookRepository.saveAll(List.of(
        new Book("Title A", "Publisher A", mark),
        new Book("Title B", "Publisher B", mark),
        new Book("Title C", "Publisher C", josh)
      ));
    };
  }
}
