package com.app.demo.dto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

/**
 * The representation of a data element.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class DataElement extends Data {
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

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    final DataElement that = (DataElement) o;
    return Objects.equals(dataElementGroups, that.dataElementGroups);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), dataElementGroups);
  }
}
