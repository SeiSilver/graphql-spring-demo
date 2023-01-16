package com.graphql.demo.silver.endpoint.errorhandler;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class GraphQLCustomError implements GraphQLError {

  private final GraphQLError graphQLError;

  public GraphQLCustomError(GraphQLError graphQLError) {
    this.graphQLError = graphQLError;
  }

  @Override
  public String getMessage() {
    return graphQLError.getMessage();
  }

  @Override
  public List<SourceLocation> getLocations() {
    return null;
  }

  @Override
  public ErrorClassification getErrorType() {
    return graphQLError.getErrorType();
  }

  @Override
  public Map<String, Object> getExtensions() {
    return graphQLError.getExtensions();
  }
}
