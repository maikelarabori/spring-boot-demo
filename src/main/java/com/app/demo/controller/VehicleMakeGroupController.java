package com.app.demo.controller;

import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.util.CollectionUtils.isEmpty;

import com.app.demo.dto.VehicleMakeGroup;
import com.app.demo.dto.VehicleMakeGroupRoot;
import com.app.demo.http.endpoint.EndpointParams;
import com.app.demo.http.endpoint.GetEndPoint;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller class responsible for exposing the endpoints
 * related to vehicle make groups.
 */
@RestController
@BasePathAwareController
public final class VehicleMakeGroupController {

  private final GetEndPoint<EndpointParams, VehicleMakeGroupRoot> vehicleMakeGroupsGetEndPoint;

  public VehicleMakeGroupController(final GetEndPoint vehicleMakeGroupsGetEndPoint) {
    this.vehicleMakeGroupsGetEndPoint = vehicleMakeGroupsGetEndPoint;
  }

  /**
   * Queries an external service in order to retrieve the vehicle make groups
   * and return it to the consumer/client.
   *
   * @return the list of vehicle make groups found, 404 (Not Found) otherwise
   */
  @GetMapping("/vehicles/makes/groups")
  public ResponseEntity<List<VehicleMakeGroup>> getVehiclesMakesGroups() {
    final String params = "id, displayName, flag";
    final VehicleMakeGroupRoot result = vehicleMakeGroupsGetEndPoint
        .consume(new EndpointParams(false, params));
    if (result != null && !isEmpty(result.getVehicleMakeGroups())) {
      return new ResponseEntity<>(result.getVehicleMakeGroups(), FOUND);
    }
    return new ResponseEntity<>(NOT_FOUND);
  }
}
