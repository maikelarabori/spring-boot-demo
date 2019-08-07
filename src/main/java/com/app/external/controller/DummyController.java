package com.app.external.controller;

import static com.app.external.data.DummyDataSource.createDummyMakeGroups;
import static com.app.external.data.DummyDataSource.createDummyMakes;
import static org.springframework.http.HttpStatus.FOUND;

import com.app.external.dto.VehicleMakeGroupRoot;
import com.app.external.dto.VehicleMakeRoot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This controller simulates an external API that requires authentication.
 * It returns some dummy data and nothing else.
 */
@RestController
@BasePathAwareController
public final class DummyController {

  final Logger logger = LoggerFactory.getLogger(DummyController.class);

  /**
   * This method is only used to simulate an external source of data.
   *
   * @return a dummy list of vehicle makes
   */
  @GetMapping("/external/vehicles-makes")
  public ResponseEntity<VehicleMakeRoot> getVehicleMakes(
      @RequestParam(required = false) final String paging,
      @RequestParam(required = false) final List<String> fields) {
    logger.info("Paging param: {}", paging);
    logger.info("Fields param: {}", fields);
    return new ResponseEntity<>(createDummyMakes(), FOUND);
  }

  /**
   * This method is only used to simulate an external source of data.
   *
   * @return a dummy list of vehicle make groups
   */
  @GetMapping("/external/vehicles-makes-groups")
  public ResponseEntity<VehicleMakeGroupRoot> getVehicleMakeGroups(
      @RequestParam(required = false) final String paging,
      @RequestParam(required = false) final List<String> fields) {
    logger.info("Paging param: {}", paging);
    logger.info("Fields param: {}", fields);
    return new ResponseEntity<>(createDummyMakeGroups(), FOUND);
  }
}
