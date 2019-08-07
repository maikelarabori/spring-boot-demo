package com.app.demo.http.endpoint;

import static org.springframework.util.Assert.hasLength;
import static org.springframework.util.Assert.notNull;

import com.app.demo.dto.VehicleMakeRoot;
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
public class VehicleMakesGetEndPoint implements GetEndPoint<EndpointParams, VehicleMakeRoot> {

  private static final String END_POINT = "/api/external/vehicles-makes";

  private final AuthWebClient authWebClient;

  public VehicleMakesGetEndPoint(final AuthWebClient authWebClient) {
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
   * @return a VehicleMakeRoot object encapsulating the original response.
   */
  @Override
  @Cacheable("vehicleMakeRoot")
  public VehicleMakeRoot consume(final EndpointParams params) {
    notNull(params, "params: cannot be null");
    hasLength(params.getFields(), "params.fields: cannot be null/empty");

    return authWebClient.get(VehicleMakeRoot.class,
        END_POINT + "?paging={hasPagination}&fields={fields}",
        params.getPaging(), params.getFields());
  }
}
