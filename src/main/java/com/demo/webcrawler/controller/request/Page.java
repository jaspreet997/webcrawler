package com.demo.webcrawler.controller.request;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"address", "links"})
public class Page {

  @JsonProperty("address")
  private String address;

  @Override
  public String toString() {
    return "Page [address=" + address + ", links=" + links + "]";
  }

  @JsonProperty("links")
  private List<String> links = null;

  @JsonProperty("address")
  public String getAddress() {
    return address;
  }

  @JsonProperty("address")
  public void setAddress(String address) {
    this.address = address;
  }

  @JsonProperty("links")
  public List<String> getLinks() {
    return links;
  }

  @JsonProperty("links")
  public void setLinks(List<String> links) {
    this.links = links;
  }

}
