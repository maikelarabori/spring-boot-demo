package com.app.external.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;
import java.util.List;

/**
 * The root element object which will hold the list of child vehicle makes.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("vehicleMakes")
public final class VehicleMakeRoot implements Serializable {
  private static final Long serialVersionUID = 1L;

  private List<VehicleMake> vehicleMakes;

  public List<VehicleMake> getVehicleMakes() {
    return vehicleMakes;
  }

  public void setVehicleMakes(final List<VehicleMake> vehicleMakes) {
    this.vehicleMakes = vehicleMakes;
  }
}
