package com.graphql.demo.silver.endpoint;

import com.graphql.demo.silver.infrastructure.entity.AuthorEntity;
import com.graphql.demo.silver.usecase.exception.DataExistedException;
import com.graphql.demo.silver.usecase.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class AuthorController {

  private final AuthorService authorService;

  @QueryMapping
  public List<AuthorEntity> findAllAuthors() {
    return authorService.findAllAuthors();
  }

  @QueryMapping
  public List<AuthorEntity> findAuthorByName(@Argument String name) {
    return authorService.findAuthorByName(name);
  }

  @MutationMapping
  public AuthorEntity createAuthor(@Argument String firstName, @Argument String lastName) throws DataExistedException {
    return authorService.createAuthor(firstName, lastName);
  }

}
