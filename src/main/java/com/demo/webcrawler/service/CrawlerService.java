package com.demo.webcrawler.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import org.springframework.stereotype.Component;
import com.demo.webcrawler.controller.request.Page;
import com.demo.webcrawler.controller.request.PageRequest;
import com.demo.webcrawler.controller.response.CrawlerResponse;

@Component
public class CrawlerService {

  public CrawlerResponse crawl(PageRequest pageRequest) {
    Set<String> visited = new HashSet<>();
    BlockingQueue<String> queue = initQueue(pageRequest);
    CrawlerResponse crawlerResponse = new CrawlerResponse();
    ExecutorService executor = Executors.newFixedThreadPool(10);
    Runnable worker = new Runnable() {
      @Override
      public void run() {
        while (!queue.isEmpty()) {
          String tempAddress = "";
          try {
            tempAddress = queue.take();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          if (visited.contains(tempAddress)) {
            crawlerResponse.addSkip(tempAddress);
          } else {
            Page page = pageRequest.checkPage(tempAddress);
            if (page != null) {
              Page nextPage = page;
              crawlerResponse.addSuccess(nextPage.getAddress());
              visited.add(nextPage.getAddress());
              List<String> links = nextPage.getLinks();
              queue.addAll(links);

            } else {
              crawlerResponse.addError(tempAddress);
            }
          }
        }
      }
    };

    while (!queue.isEmpty()) {
      executor.submit(worker);
    }

    executor.shutdown();
    return crawlerResponse;
  }

  private BlockingQueue<String> initQueue(PageRequest pageRequest) {
    BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    List<Page> pages = pageRequest.getPages();
    if (!pages.isEmpty()) {
      try {
        queue.put(pages.get(0).getAddress());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    return queue;
  }


}
