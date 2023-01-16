package com.graphql.demo.silver.endpoint;

import com.graphql.demo.silver.infrastructure.entity.BookEntity;
import com.graphql.demo.silver.usecase.exception.DataExistedException;
import com.graphql.demo.silver.usecase.exception.DataNotFoundException;
import com.graphql.demo.silver.usecase.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class BookController {

  private final BookService bookService;

  @QueryMapping
  public List<BookEntity> findAllBook() {
    return bookService.findAllBook();
  }

  @QueryMapping
  public List<BookEntity> findBookByTitle(@Argument String title) {
    return bookService.findBookByTitle(title);
  }

  @MutationMapping
  public BookEntity createBook(
      @Argument String title,
      @Argument String description,
      @Argument Integer authorId
  ) throws DataNotFoundException, DataExistedException {
    return bookService.createBook(title, description, authorId);
  }

  @MutationMapping
  public BookEntity updateBook(
      @Argument Integer id,
      @Argument String title,
      @Argument String description,
      @Argument Integer authorId
  ) throws DataNotFoundException {
    return bookService.updateBook(id, title, description, authorId);
  }

  @MutationMapping
  public boolean deleteBook(
      @Argument Integer id
  ) throws DataNotFoundException {
    return bookService.deleteBook(id);
  }
}
