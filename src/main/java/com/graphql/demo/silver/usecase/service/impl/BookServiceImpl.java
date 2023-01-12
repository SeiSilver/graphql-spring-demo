package com.graphql.demo.silver.usecase.service.impl;

import com.graphql.demo.silver.infrastructure.entity.AuthorEntity;
import com.graphql.demo.silver.infrastructure.entity.BookEntity;
import com.graphql.demo.silver.infrastructure.enums.BookStatus;
import com.graphql.demo.silver.infrastructure.repository.AuthorRepository;
import com.graphql.demo.silver.infrastructure.repository.BookRepository;
import com.graphql.demo.silver.usecase.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;

  @Override
  public List<BookEntity> findAllBook() {
    return bookRepository.findAll();
  }

  @Override
  public List<BookEntity> findBookByTitle(String title) {
    return bookRepository.findByTitle(title);
  }

  @Override
  @Transactional
  public BookEntity createBook(String title, String desc, Integer authorId) {
    Optional<AuthorEntity> authorOpt = authorRepository.findById(authorId);
    return bookRepository.save(BookEntity.builder()
        .title(title)
        .description(desc)
        .status(BookStatus.NEW)
        .author(authorOpt.orElseThrow(() -> new RuntimeException("Author is not existed!")))
        .build());
  }

  @Override
  @Transactional
  public BookEntity updateBook(Integer id, String title, String desc, Integer authorId) {
    Optional<BookEntity> bookOpt = bookRepository.findById(id);
    if (bookOpt.isEmpty()) {
      throw new RuntimeException("Book not existed");
    }
    Optional<AuthorEntity> authorOpt = authorRepository.findById(authorId);
    if (authorOpt.isEmpty()) {
      throw new RuntimeException("Author is not existed!");
    }
    var newBook = bookOpt.get();
    newBook.setAuthor(authorOpt.get());
    newBook.setDescription(desc);
    newBook.setTitle(title);
    return bookRepository.save(newBook);
  }

  @Override
  @Transactional
  public boolean deleteBook(Integer id) {
    Optional<BookEntity> bookOpt = bookRepository.findById(id);
    if (bookOpt.isEmpty()) {
      return false;
    }
    bookRepository.delete(bookOpt.get());
    return true;
  }
}
