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
    public String scrapeSources() {
        // Call the asynchronous initiation method
        pythonScrapingServiceGrpc.initiateScrape();
        // Return an immediate response
        return "Scraping process initiated successfully.";
    }

    // New endpoint to add a source (assuming this remains synchronous)
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