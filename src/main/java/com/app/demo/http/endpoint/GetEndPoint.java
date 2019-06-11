package com.app.demo.http.endpoint;

import java.io.Serializable;

/**
 * Interface used to facilitate the implementation of self-contained
 * GET endpoints based on pre-defined query params.
 *
 * @param <E> the pre-defined query params
 * @param <T> an object to encapsulated the responses
 */
public interface GetEndPoint<E extends QueryParams, T extends Serializable> {

  /**
   * Should holds the main code responsible for consuming external
   * service providers and serialize it into the return object of
   * this method.
   *
   * @param params, the query params
   * @return an object encapsulating the original response
   */
  T consume(E params);
}
