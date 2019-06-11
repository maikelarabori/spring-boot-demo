package com.app.demo.dto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

/**
 * The representation of data element group.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class DataElementGroup extends Data {
  private static final Long serialVersionUID = 1L;

  private List<DataElement> dataElements;

  @JsonProperty("members")
  public List<DataElement> getDataElements() {
    return dataElements;
  }

  @JsonProperty("dataElements")
  public void setDataElements(final List<DataElement> dataElements) {
    this.dataElements = dataElements;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    final DataElementGroup that = (DataElementGroup) o;
    return Objects.equals(dataElements, that.dataElements);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), dataElements);
  }
}
