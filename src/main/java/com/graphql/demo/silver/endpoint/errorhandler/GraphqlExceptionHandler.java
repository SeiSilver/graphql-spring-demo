package com.graphql.demo.silver.endpoint.errorhandler;

import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

@Component
public class GraphqlExceptionHandler extends DataFetcherExceptionResolverAdapter {

  @Override
  protected GraphQLCustomError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
    return buildError(ex, env);
  }

  private GraphQLCustomError buildError(Throwable ex, DataFetchingEnvironment env) {
    return new GraphQLCustomError(GraphqlErrorBuilder.newError(env)
        .errorType(ErrorType.INTERNAL_ERROR)
        .message(ex.getMessage())
        .build());
  }

}
