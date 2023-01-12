package com.graphql.demo.silver.infrastructure.repository;

import com.graphql.demo.silver.infrastructure.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Integer> {

  Optional<AuthorEntity> findByFirstNameContainingIgnoreCase(String firstName);

  @Query("SELECT a FROM AuthorEntity a " +
      "WHERE a.firstName LIKE %:name% or a.lastName LIKE %:name%")
  List<AuthorEntity> findByName(String name);

  boolean existsByFirstNameAndLastName(String firstName, String lastName);

}
