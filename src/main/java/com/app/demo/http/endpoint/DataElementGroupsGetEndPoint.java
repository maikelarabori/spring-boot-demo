package com.app.demo.http.endpoint;

import static org.springframework.util.Assert.hasLength;
import static org.springframework.util.Assert.notNull;

import com.app.demo.dto.DataElementGroupRoot;
import com.app.demo.http.AuthWebClient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * A self-contained component that will retrieve data from its respective
 * external service provider using REST.
 *
 * @see GetEndPoint
 */
@Component
public class DataElementGroupsGetEndPoint implements GetEndPoint<EndpointParams, DataElementGroupRoot> {

  private static final String END_POINT = "/api/external/data-element-groups";

  private final AuthWebClient authWebClient;

  public DataElementGroupsGetEndPoint(final AuthWebClient authWebClient) {
    this.authWebClient = authWebClient;
  }

  /**
   * @see GetEndPoint
   *
   * This method also provides caching capabilites and will cache each
   * similar requests for a pre-defined amount of time defined at:
   *
   * File: application.properties
   * Property: spring.cache.caffeine.spec
   * Attribute: expireAfterWrite=1m
   *
   * @param params to feed the endpoint query
   * @return a DataElementGroupRoot object encapsulating the original response.
   */
  @Override
  @Cacheable("dataElementGroupRoot")
  public DataElementGroupRoot consume(final EndpointParams params) {
    notNull(params, "params: cannot be null");
    hasLength(params.getFields(), "params.fields: cannot be null/empty");

    return authWebClient.get(DataElementGroupRoot.class,
        END_POINT + "?paging={hasPagination}&fields={fields}",
            params.getPaging(), params.getFields());
  }
}
