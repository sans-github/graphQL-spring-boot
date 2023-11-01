package com.san.controller;

import com.san.model.Author;
import com.san.model.Book;
import com.san.repository.AuthorRepository;
import com.san.repository.BookRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class AuthorController {

  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;

  public AuthorController(AuthorRepository authorRepository, BookRepository bookRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
  }

  @QueryMapping
  Iterable<Author> authors () {
    return authorRepository.findAll();
  }

  @QueryMapping
  Optional<Author> authorById (@Argument Long id) {
    return authorRepository.findById(id);
  }

  @MutationMapping
  Optional<Book> addBook (@Argument BookInput book) {
    Author author = authorRepository.findById(book.authorId).orElseThrow(() -> {
      throw new IllegalArgumentException("Author not found");
    });
    
    return Optional.of(bookRepository.save(new Book(book.title, book.publisher, author)));
  }

 //Cool new construct in newer version of java. 
  //  Record classes, which are a special kind of class, help to model plain data aggregates with less ceremony than normal classes.
  record BookInput(String title, String publisher, Long authorId) {}
}
