package com.app.demo.controller;

import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.util.CollectionUtils.isEmpty;

import com.app.demo.dto.DataElement;
import com.app.demo.dto.DataElementRoot;
import com.app.demo.http.endpoint.EndpointParams;
import com.app.demo.http.endpoint.GetEndPoint;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller class responsible for exposing the endpoints
 * related to data elements.
 */
@RestController
@BasePathAwareController
public final class DataElementController {

  private final GetEndPoint<EndpointParams, DataElementRoot> dataElementsGetEndPoint;

  public DataElementController(final GetEndPoint dataElementsGetEndPoint) {
    this.dataElementsGetEndPoint = dataElementsGetEndPoint;
  }

  /**
   * Queries an external service in order to retrieve the data elements
   * and return it to the consumer/client.
   *
   * @return the list of data elements found, 404 (Not Found) otherwise
   */
  @GetMapping("/data/elements")
  public ResponseEntity<List<DataElement>> getDataElements() {
    final String params = "id, displayName, dataElementGroups[id]";
    final DataElementRoot result = dataElementsGetEndPoint
        .consume(new EndpointParams(false, params));
    if (result != null && !isEmpty(result.getDataElements())) {
      return new ResponseEntity<>(result.getDataElements(), FOUND);
    }
    return new ResponseEntity<>(NOT_FOUND);
  }
}
