package com.app.external.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;
import java.util.List;

/**
 * The root element object which will hold the list of child data elements.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("dataElements")
public final class DataElementRoot implements Serializable {
  private static final Long serialVersionUID = 1L;

  private List<DataElement> dataElements;

  public List<DataElement> getDataElements() {
    return dataElements;
  }

  public void setDataElements(final List<DataElement> dataElements) {
    this.dataElements = dataElements;
  }
}
