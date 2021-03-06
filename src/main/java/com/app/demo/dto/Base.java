package com.app.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 * The super class holding common attributes to be reused
 * by its subclasses.
 */
public class Base implements Serializable {
  private static final Long serialVersionUID = 1L;

  private String id;
  private String displayName;

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  @JsonProperty("name")
  public String getDisplayName() {
    return displayName;
  }

  @JsonProperty("displayName")
  public void setDisplayName(final String displayName) {
    this.displayName = displayName;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final Base base = (Base) o;
    return Objects.equals(id, base.id) &&
        Objects.equals(displayName, base.displayName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, displayName);
  }
}
