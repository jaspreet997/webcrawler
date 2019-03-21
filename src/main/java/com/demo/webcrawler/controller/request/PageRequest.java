package com.demo.webcrawler.controller.request;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"pages"})
public class PageRequest {

  @JsonProperty("pages")
  private List<Page> pages = null;

  @JsonProperty("pages")
  public List<Page> getPages() {
    return pages;
  }

  @JsonProperty("pages")
  public void setPages(List<Page> pages) {
    this.pages = pages;
  }

  @Override
  public String toString() {
    return "PageRequest [pages=" + pages + "]";
  }

  public Page checkPage(String address) {
    for (Page page : pages) {
      if (page.getAddress().equals(address)) {
        return page;
      }
    }
    return null;
  }
}
