package com.africainsight.newsservice.controller;

import com.africainsight.newsservice.dto.AddSourceRequest;
import com.africainsight.newsservice.grpc.PythonScrapingServiceGrpc;
import org.springframework.web.bind.annotation.*;
import source.SourceProto;


@RestController
@RequestMapping("/admin/grpc")
public class GrpcController {
  private final PythonScrapingServiceGrpc pythonScrapingServiceGrpc;

  public GrpcController(PythonScrapingServiceGrpc pythonScrapingServiceGrpc) {
    this.pythonScrapingServiceGrpc = pythonScrapingServiceGrpc;
  }

  @GetMapping("/scrape")
  public String scrapeSources(){
    SourceProto.ScrapeResponse response = pythonScrapingServiceGrpc.scrapeSources();
    return response.getMessage();
  }

  // New endpoint to add a source
  @PostMapping("/sources")
  public String addSource(@RequestBody AddSourceRequest request) {
    SourceProto.SourceResponse response = pythonScrapingServiceGrpc.addSource(
        request.getUrl(),
        request.isContainsAiContent(),
        request.isContainsAfricaContent()
    );
    return response.getMessage();
  }
}
