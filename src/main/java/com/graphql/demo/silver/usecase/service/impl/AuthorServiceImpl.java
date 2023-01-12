package com.graphql.demo.silver.usecase.service.impl;

import com.graphql.demo.silver.infrastructure.entity.AuthorEntity;
import com.graphql.demo.silver.infrastructure.repository.AuthorRepository;
import com.graphql.demo.silver.usecase.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

  private final AuthorRepository authorRepository;

  @Override
  public List<AuthorEntity> findAllAuthors() {
    return authorRepository.findAll();
  }

  @Override
  public List<AuthorEntity> findAuthorByName(String name) {
    return authorRepository.findByName(name);
  }

  @Override
  @Transactional
  public AuthorEntity createAuthor(String firstName, String lastName) {
    if (authorRepository.existsByFirstNameAndLastName(firstName, lastName)) {
      throw new RuntimeException("Author existed!");
    }
    return authorRepository.save(AuthorEntity.builder()
        .firstName(firstName)
        .lastName(lastName)
        .build());
  }

}
