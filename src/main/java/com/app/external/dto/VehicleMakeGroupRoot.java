package com.app.external.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;
import java.util.List;

/**
 * The root group object which will hold the list of vehicle make groups.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("vehicleMakeGroups")
public final class VehicleMakeGroupRoot implements Serializable {
  private static final Long serialVersionUID = 1L;

  private List<VehicleMakeGroup> vehicleMakeGroups;

  public List<VehicleMakeGroup> getVehicleMakeGroups() {
    return vehicleMakeGroups;
  }

  public void setVehicleMakeGroups(final List<VehicleMakeGroup> vehicleMakeGroups) {
    this.vehicleMakeGroups = vehicleMakeGroups;
  }
}
