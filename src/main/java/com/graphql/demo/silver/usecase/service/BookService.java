package com.graphql.demo.silver.usecase.service;

import com.graphql.demo.silver.infrastructure.entity.BookEntity;
import com.graphql.demo.silver.usecase.exception.DataExistedException;
import com.graphql.demo.silver.usecase.exception.DataNotFoundException;

import java.util.List;

public interface BookService {

  List<BookEntity> findAllBook();

  List<BookEntity> findBookByTitle(String title);

  BookEntity createBook(String title, String desc, Integer authorId) throws DataNotFoundException, DataExistedException;

  BookEntity updateBook(Integer id, String title, String desc, Integer authorId) throws DataNotFoundException;

  boolean deleteBook(Integer id) throws DataNotFoundException;

}
