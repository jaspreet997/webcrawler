package com.demo.webcrawler.controller.response;

import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CrawlerResponse {
  private final Set<String> success;
  private final Set<String> skip;
  private final Set<String> error;

  public CrawlerResponse() {
    success = new HashSet<>();
    skip = new HashSet<>();
    error = new HashSet<>();
  }

  public CrawlerResponse(Set<String> success, Set<String> skip, Set<String> error) {
    this.success = success;
    this.skip = skip;
    this.error = error;
  }

  public Set<String> getSuccess() {
    return success;
  }

  public Set<String> getSkipped() {
    return skip;
  }

  public Set<String> getErrors() {
    return error;
  }

  public boolean addSuccess(String nextAddress) {
    return success.add(nextAddress);
  }

  public boolean addSkip(String nextAddress) {
    return skip.add(nextAddress);
  }

  public boolean addError(String nextAddress) {
    return error.add(nextAddress);
  }

  public String getResponse() {
    ObjectMapper mapper = new ObjectMapper();
    StringBuilder result = new StringBuilder();
    try {
      result.append("Success:");
      result.append("\n");
      result.append(mapper.writeValueAsString(success));
      result.append("\n");
      result.append("Skipped:");
      result.append("\n");
      result.append(mapper.writeValueAsString(skip));
      result.append("\n");
      result.append("Error:");
      result.append("\n");
      result.append(mapper.writeValueAsString(error));
      return result.toString();
    } catch (JsonProcessingException e) {
      return ("JsonProcessingException");
    }
  }
}
