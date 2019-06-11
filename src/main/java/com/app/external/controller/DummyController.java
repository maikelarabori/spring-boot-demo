package com.app.external.controller;

import static com.app.external.data.DummyDataSource.createDummyElementGroups;
import static com.app.external.data.DummyDataSource.createDummyElements;
import static org.springframework.http.HttpStatus.FOUND;

import com.app.external.dto.DataElementGroupRoot;
import com.app.external.dto.DataElementRoot;
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
   * @return a dummy list of data elements
   */
  @GetMapping("/external/data-elements")
  public ResponseEntity<DataElementRoot> getDataElements(
      @RequestParam(required = false) final String paging,
      @RequestParam(required = false) final List<String> fields) {
    logger.info("Paging param: {}", paging);
    logger.info("Fields param: {}", fields);
    return new ResponseEntity<>(createDummyElements(), FOUND);
  }

  /**
   * This method is only used to simulate an external source of data.
   *
   * @return a dummy list of data element groups
   */
  @GetMapping("/external/data-element-groups")
  public ResponseEntity<DataElementGroupRoot> getDataElementGroups(
      @RequestParam(required = false) final String paging,
      @RequestParam(required = false) final List<String> fields) {
    logger.info("Paging param: {}", paging);
    logger.info("Fields param: {}", fields);
    return new ResponseEntity<>(createDummyElementGroups(), FOUND);
  }
}
