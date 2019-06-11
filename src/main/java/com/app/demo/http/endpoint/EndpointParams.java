package com.app.demo.http.endpoint;

import java.util.Objects;

public final class EndpointParams implements QueryParams {

  private Boolean paging = false;
  private String fields;

  public EndpointParams() {}

  public EndpointParams(final Boolean paging, final String fields) {
    this.paging = paging;
    this.fields = fields;
  }

  public Boolean getPaging() {
    return paging;
  }

  public void setPaging(final Boolean paging) {
    this.paging = paging;
  }

  public String getFields() {
    return fields;
  }

  public void setFields(final String fields) {
    this.fields = fields;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final EndpointParams params = (EndpointParams) o;
    return Objects.equals(paging, params.paging) &&
        Objects.equals(fields, params.fields);
  }

  @Override
  public int hashCode() {
    return Objects.hash(paging, fields);
  }
}
