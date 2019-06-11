package com.app.demo.controller;

import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.util.CollectionUtils.isEmpty;

import com.app.demo.dto.DataElementGroup;
import com.app.demo.dto.DataElementGroupRoot;
import com.app.demo.http.endpoint.EndpointParams;
import com.app.demo.http.endpoint.GetEndPoint;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller class responsible for exposing the endpoints
 * related to data element groups.
 */
@RestController
@BasePathAwareController
public final class DataElementGroupController {

  private final GetEndPoint<EndpointParams, DataElementGroupRoot> dataElementGroupsGetEndPoint;

  public DataElementGroupController(final GetEndPoint dataElementGroupsGetEndPoint) {
    this.dataElementGroupsGetEndPoint = dataElementGroupsGetEndPoint;
  }

  /**
   * Queries an external service in order to retrieve the element groups
   * and return it to the consumer/client.
   *
   * @return the list of element groups found, 404 (Not Found) otherwise
   */
  @GetMapping("/data/element/groups")
  public ResponseEntity<List<DataElementGroup>> getDataElementGroups() {
    final String params = "id, displayName, dataElements[id]";
    final DataElementGroupRoot result = dataElementGroupsGetEndPoint
        .consume(new EndpointParams(false, params));
    if (result != null && !isEmpty(result.getDataElementGroups())) {
      return new ResponseEntity<>(result.getDataElementGroups(), FOUND);
    }
    return new ResponseEntity<>(NOT_FOUND);
  }
}
