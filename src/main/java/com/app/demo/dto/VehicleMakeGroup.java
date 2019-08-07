package com.app.demo.dto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

/**
 * The representation of vehicle make group.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class VehicleMakeGroup extends Base {
  private static final Long serialVersionUID = 1L;

  private List<VehicleMake> vehicleMakes;

  @JsonProperty("makes")
  public List<VehicleMake> getVehicleMakes() {
    return vehicleMakes;
  }

  @JsonProperty("vehicleMakes")
  public void setVehicleMakes(final List<VehicleMake> vehicleMakes) {
    this.vehicleMakes = vehicleMakes;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    final VehicleMakeGroup that = (VehicleMakeGroup) o;
    return Objects.equals(vehicleMakes, that.vehicleMakes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), vehicleMakes);
  }
}
