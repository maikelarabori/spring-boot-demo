package com.app.external.data;

import com.app.external.dto.DataElement;
import com.app.external.dto.DataElementGroup;
import com.app.external.dto.DataElementGroupRoot;
import com.app.external.dto.DataElementRoot;

import java.util.ArrayList;
import java.util.List;

public final class DummyDataSource {

  public static DataElementRoot createDummyElements() {
    final DataElement element = new DataElement();
    element.setDisplayName("aMemberName");
    element.setId("aMemberId");

    final DataElementGroup group = new DataElementGroup();
    group.setId(String.valueOf(System.currentTimeMillis()));

    final List<DataElementGroup> groups = new ArrayList<>();
    groups.add(group);

    final List<DataElement> elements = new ArrayList<>();
    element.setDataElementGroups(groups);
    elements.add(element);

    final DataElementRoot dataElementRoot = new DataElementRoot();
    dataElementRoot.setDataElements(elements);

    return dataElementRoot;
  }

  public static DataElementGroupRoot createDummyElementGroups() {
    final DataElement element = new DataElement();
    element.setId("aMemberId");

    final DataElementGroup group = new DataElementGroup();
    group.setId(String.valueOf(System.currentTimeMillis()));
    group.setDisplayName("aGroupName");

    final List<DataElement> elements = new ArrayList<>();
    elements.add(element);

    final List<DataElementGroup> groups = new ArrayList<>();
    group.setDataElements(elements);
    groups.add(group);

    final DataElementGroupRoot dataElementGroupRoot = new DataElementGroupRoot();
    dataElementGroupRoot.setDataElementGroups(groups);

    return dataElementGroupRoot;
  }
}
