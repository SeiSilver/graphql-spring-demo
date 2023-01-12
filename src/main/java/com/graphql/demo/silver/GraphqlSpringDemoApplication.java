package com.graphql.demo.silver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.ZonedDateTime;
import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing(
    dateTimeProviderRef = "auditingDateTimeProvider"
)
public class GraphqlSpringDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(GraphqlSpringDemoApplication.class, args);
  }

  @Bean
  public DateTimeProvider auditingDateTimeProvider() {
    return () -> Optional.of(ZonedDateTime.now());
  }

}
