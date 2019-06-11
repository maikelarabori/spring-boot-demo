package com.app.demo;

import com.app.demo.dto.DataElement;
import com.app.demo.dto.DataElementGroup;
import com.app.demo.dto.DataElementGroupRoot;
import com.app.demo.dto.DataElementRoot;

import java.util.ArrayList;
import java.util.List;

public final class StubProvider {
  public static final DataElementRoot stubDataElementRoot() {
    final DataElement element = new DataElement();
    element.setDisplayName("memberName");
    element.setId("memberId");

    final DataElementGroup group = new DataElementGroup();
    group.setId("groupId");

    final List<DataElementGroup> groups = new ArrayList<>();
    groups.add(group);

    final List<DataElement> elements = new ArrayList<>();
    element.setDataElementGroups(groups);
    elements.add(element);

    final DataElementRoot dataElementRoot = new DataElementRoot();
    dataElementRoot.setDataElements(elements);

    return dataElementRoot;
  }

  public static final DataElementGroupRoot stubDataElementGroupRoot() {
    final DataElement element = new DataElement();
    element.setId("memberId");

    final DataElementGroup group = new DataElementGroup();
    group.setId("groupId");
    group.setDisplayName("groupName");

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
