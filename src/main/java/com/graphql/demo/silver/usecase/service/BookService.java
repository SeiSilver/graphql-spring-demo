package com.graphql.demo.silver.usecase.service;

import com.graphql.demo.silver.infrastructure.entity.BookEntity;

import java.util.List;

public interface BookService {

  List<BookEntity> findAllBook();

  List<BookEntity> findBookByTitle(String title);

  BookEntity createBook(String title, String desc, Integer authorId);

  BookEntity updateBook(Integer id, String title, String desc, Integer authorId);

  boolean deleteBook(Integer id);

}
