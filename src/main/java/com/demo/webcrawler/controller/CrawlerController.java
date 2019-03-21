package com.demo.webcrawler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.demo.webcrawler.controller.request.PageRequest;
import com.demo.webcrawler.controller.response.CrawlerResponse;
import com.demo.webcrawler.service.CrawlerService;

@RestController
public class CrawlerController {

  @Autowired
  CrawlerService crawlerService;

  @PostMapping("/crawlInternet")
  public String crawlInternet(@RequestBody PageRequest pageRequest) {
    final CrawlerResponse crawl = crawlerService.crawl(pageRequest);
    return crawl.getResponse();
  }
}
