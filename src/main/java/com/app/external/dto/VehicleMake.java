package com.app.external.dto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Objects;

/**
 * The representation of a vehicle make.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class VehicleMake extends Base {
  private static final Long serialVersionUID = 1L;

  private List<VehicleMakeGroup> vehicleMakeGroups;

  public List<VehicleMakeGroup> getVehicleMakeGroups() {
    return vehicleMakeGroups;
  }

  public void setVehicleMakeGroups(final List<VehicleMakeGroup> vehicleMakeGroups) {
    this.vehicleMakeGroups = vehicleMakeGroups;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    final VehicleMake that = (VehicleMake) o;
    return Objects.equals(vehicleMakeGroups, that.vehicleMakeGroups);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), vehicleMakeGroups);
  }
}
