package com.graphql.demo.silver.usecase.service;

import com.graphql.demo.silver.infrastructure.entity.AuthorEntity;
import com.graphql.demo.silver.usecase.exception.DataExistedException;

import java.util.List;

public interface AuthorService {

  List<AuthorEntity> findAllAuthors();

  List<AuthorEntity> findAuthorByName(String name);

  AuthorEntity createAuthor(String firstName, String lastName) throws DataExistedException;

}
