package com.graphql.demo.silver.testing;

import com.graphql.demo.silver.infrastructure.entity.AuthorEntity;
import com.graphql.demo.silver.infrastructure.entity.BookEntity;
import com.graphql.demo.silver.infrastructure.enums.BookStatus;
import com.graphql.demo.silver.infrastructure.repository.AuthorRepository;
import com.graphql.demo.silver.infrastructure.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@AllArgsConstructor
public class InitData {

  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;

  @PostConstruct
  private void init() {
    initAuthor();
    initBook();
  }

  private void initBook() {

    var author = authorRepository.findByFirstNameContainingIgnoreCase("Silver");
    if (author.isPresent()) {
      List<BookEntity> bookEntities = List.of(
          BookEntity.builder().description("Yuri").title("Neko").status(BookStatus.NEW).pages(10).author(author.get()).build(),
          BookEntity.builder().description("Isekai").title("Aki").status(BookStatus.NEW).pages(20).author(author.get()).build(),
          BookEntity.builder().description("Hero").title("Yuki").status(BookStatus.NEW).pages(30).author(author.get()).build()
      );
      bookEntities.forEach(b -> {
        if (bookRepository.findByTitle(b.getTitle()).isEmpty()) {
          bookRepository.save(b);
        }
      });
    }

  }

  private void initAuthor() {

    List<AuthorEntity> authors = List.of(
        AuthorEntity.builder().firstName("Silver").lastName("Neko").build(),
        AuthorEntity.builder().firstName("author1").lastName("Aki").build(),
        AuthorEntity.builder().firstName("author2").lastName("Yuki").build(),
        AuthorEntity.builder().firstName("author4").lastName("Mahiru").build(),
        AuthorEntity.builder().firstName("author5").lastName("Kiko").build()
    );

    authors.forEach(a -> {
      if (authorRepository.findByName(a.getFirstName()).isEmpty()) {
        authorRepository.save(a);
      }
    });

  }


}
