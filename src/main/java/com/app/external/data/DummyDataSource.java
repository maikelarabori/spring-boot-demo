package com.app.external.data;

import com.app.external.dto.VehicleMake;
import com.app.external.dto.VehicleMakeGroup;
import com.app.external.dto.VehicleMakeGroupRoot;
import com.app.external.dto.VehicleMakeRoot;

import java.util.ArrayList;
import java.util.List;

public final class DummyDataSource {

  public static VehicleMakeRoot createDummyMakes() {
    final VehicleMake vehicleMake = new VehicleMake();
    vehicleMake.setDisplayName("aMakeName");
    vehicleMake.setId("aMakeId");

    final VehicleMakeGroup group = new VehicleMakeGroup();
    group.setId(String.valueOf(System.currentTimeMillis()));

    final List<VehicleMakeGroup> groups = new ArrayList<>();
    groups.add(group);

    final List<VehicleMake> vehicleMakes = new ArrayList<>();
    vehicleMake.setVehicleMakeGroups(groups);
    vehicleMakes.add(vehicleMake);

    final VehicleMakeRoot vehicleMakeRoot = new VehicleMakeRoot();
    vehicleMakeRoot.setVehicleMakes(vehicleMakes);

    return vehicleMakeRoot;
  }

  public static VehicleMakeGroupRoot createDummyMakeGroups() {
    final VehicleMake vehicleMake = new VehicleMake();
    vehicleMake.setId("aMakeId");

    final VehicleMakeGroup group = new VehicleMakeGroup();
    group.setId(String.valueOf(System.currentTimeMillis()));
    group.setDisplayName("aMakeGroupName");

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
