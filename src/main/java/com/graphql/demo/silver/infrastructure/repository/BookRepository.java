package com.graphql.demo.silver.infrastructure.repository;

import com.graphql.demo.silver.infrastructure.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
  @Query("SELECT b FROM BookEntity b " +
      "WHERE b.title like %:title% ")
  List<BookEntity> findByTitle(String title);

}
