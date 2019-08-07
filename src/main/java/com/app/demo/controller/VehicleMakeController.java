package com.app.demo.controller;

import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.util.CollectionUtils.isEmpty;

import com.app.demo.dto.VehicleMake;
import com.app.demo.dto.VehicleMakeRoot;
import com.app.demo.http.endpoint.EndpointParams;
import com.app.demo.http.endpoint.GetEndPoint;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller class responsible for exposing the endpoints
 * related to vehicle makes.
 */
@RestController
@BasePathAwareController
public final class VehicleMakeController {

  private final GetEndPoint<EndpointParams, VehicleMakeRoot> vehicleMakesGetEndPoint;

  public VehicleMakeController(final GetEndPoint vehicleMakesGetEndPoint) {
    this.vehicleMakesGetEndPoint = vehicleMakesGetEndPoint;
  }

  /**
   * Queries an external service in order to retrieve the vehicle makes
   * and return it to the consumer/client.
   *
   * @return the list of vehicle makes found, 404 (Not Found) otherwise
   */
  @GetMapping("/vehicles/makes")
  public ResponseEntity<List<VehicleMake>> getVehiclesMakes() {
    final String params = "id, displayName";
    final VehicleMakeRoot result = vehicleMakesGetEndPoint
        .consume(new EndpointParams(false, params));
    if (result != null && !isEmpty(result.getVehicleMakes())) {
      return new ResponseEntity<>(result.getVehicleMakes(), FOUND);
    }
    return new ResponseEntity<>(NOT_FOUND);
  }
}
