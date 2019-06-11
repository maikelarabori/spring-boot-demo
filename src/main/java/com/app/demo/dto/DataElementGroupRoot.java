package com.app.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;
import java.util.List;

/**
 * The root group object which will hold the list of child element groups.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("dataElementGroups")
public final class DataElementGroupRoot implements Serializable {
  private static final Long serialVersionUID = 1L;

  private List<DataElementGroup> dataElementGroups;

  @JsonProperty("groups")
  public List<DataElementGroup> getDataElementGroups() {
    return dataElementGroups;
  }

  @JsonProperty("dataElementGroups")
  public void setDataElementGroups(final List<DataElementGroup> dataElementGroups) {
    this.dataElementGroups = dataElementGroups;
  }
}
