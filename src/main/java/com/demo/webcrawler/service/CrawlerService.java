package com.demo.webcrawler.service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import org.springframework.stereotype.Component;
import com.demo.webcrawler.controller.request.Page;
import com.demo.webcrawler.controller.request.PageRequest;
import com.demo.webcrawler.controller.response.CrawlerResponse;
import com.google.common.collect.Iterables;

@Component
public class CrawlerService {

  public CrawlerResponse crawl(PageRequest pageRequest) {
    Set<String> visited = new HashSet<>();
    Queue<String> queue = initQueue(pageRequest);

    CrawlerResponse crawlerResponse = new CrawlerResponse();

    while (!queue.isEmpty()) {
      String nextAddress = queue.remove();
      if (visited.contains(nextAddress)) {
        crawlerResponse.addSkip(nextAddress);
      } else {
        Page page = pageRequest.checkPage(nextAddress);
        if (page != null) {
          Page nextPage = page;
          crawlerResponse.addSuccess(nextPage.getAddress());
          visited.add(nextPage.getAddress());
          List<String> links = nextPage.getLinks();
          Iterables.addAll(queue, links);
        } else {
          crawlerResponse.addError(nextAddress);
        }
      }
    }
    return crawlerResponse;
  }

  private Queue<String> initQueue(PageRequest pageRequest) {
    Queue<String> queue = new LinkedList<>();
    List<Page> pages = pageRequest.getPages();
    if (!pages.isEmpty()) {
      queue.add(pages.get(0).getAddress());
    }
    return queue;
  }


}
