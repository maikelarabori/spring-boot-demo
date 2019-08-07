package com.app.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;
import java.util.List;

/**
 * The root group object which will hold the list of child make groups.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("vehicleMakeGroups")
public final class VehicleMakeGroupRoot implements Serializable {
  private static final Long serialVersionUID = 1L;

  private List<VehicleMakeGroup> vehicleMakeGroups;

  @JsonProperty("groups")
  public List<VehicleMakeGroup> getVehicleMakeGroups() {
    return vehicleMakeGroups;
  }

  @JsonProperty("vehicleMakeGroups")
  public void setVehicleMakeGroups(final List<VehicleMakeGroup> vehicleMakeGroups) {
    this.vehicleMakeGroups = vehicleMakeGroups;
  }
}
