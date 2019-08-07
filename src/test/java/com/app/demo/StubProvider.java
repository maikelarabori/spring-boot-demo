package com.app.demo;

import com.app.demo.dto.VehicleMake;
import com.app.demo.dto.VehicleMakeGroup;
import com.app.demo.dto.VehicleMakeGroupRoot;
import com.app.demo.dto.VehicleMakeRoot;

import java.util.ArrayList;
import java.util.List;

public final class StubProvider {
  public static final VehicleMakeRoot stubVehicleMakeRoot() {
    final VehicleMake vehicleMake = new VehicleMake();
    vehicleMake.setDisplayName("makeName");
    vehicleMake.setId("makeId");

    final VehicleMakeGroup group = new VehicleMakeGroup();
    group.setId("makeGroupId");

    final List<VehicleMakeGroup> groups = new ArrayList<>();
    groups.add(group);

    final List<VehicleMake> vehicleMakes = new ArrayList<>();
    vehicleMake.setVehicleMakeGroups(groups);
    vehicleMakes.add(vehicleMake);

    final VehicleMakeRoot vehicleMakeRoot = new VehicleMakeRoot();
    vehicleMakeRoot.setVehicleMakes(vehicleMakes);

    return vehicleMakeRoot;
  }

  public static final VehicleMakeGroupRoot stubVehicleMakeGroupRoot() {
    final VehicleMake vehicleMake = new VehicleMake();
    vehicleMake.setId("makeId");

    final VehicleMakeGroup group = new VehicleMakeGroup();
    group.setId("makeGroupId");
    group.setDisplayName("makeGroupName");

    final List<VehicleMake> vehicleMakes = new ArrayList<>();
    vehicleMakes.add(vehicleMake);

    final List<VehicleMakeGroup> groups = new ArrayList<>();
    group.setVehicleMakes(vehicleMakes);
    groups.add(group);

    final VehicleMakeGroupRoot vehicleMakeGroupRoot = new VehicleMakeGroupRoot();
    vehicleMakeGroupRoot.setVehicleMakeGroups(groups);

    return vehicleMakeGroupRoot;
  }
}
