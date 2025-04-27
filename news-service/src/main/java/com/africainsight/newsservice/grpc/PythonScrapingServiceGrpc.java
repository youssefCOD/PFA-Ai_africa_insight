
package com.africainsight.newsservice.grpc;

import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import source.SourceProto;
import source.SourceServiceGrpc;

// Import your generated gRPC classes
//import your.package.path.SourceProto; // Replace with your actual package path
//import your.package.path.SourceServiceGrpc; // Replace with your actual package path

@Service
public class PythonScrapingServiceGrpc {
    private static final Logger log = LoggerFactory.getLogger(PythonScrapingServiceGrpc.class);
    // Use FutureStub for asynchronous calls
    private final SourceServiceGrpc.SourceServiceFutureStub futureStub;
    // Keep BlockingStub for synchronous calls like addSource if needed
    private final SourceServiceGrpc.SourceServiceBlockingStub blockingStub;
    private final ManagedChannel channel; // Keep channel reference for shutdown

    public PythonScrapingServiceGrpc(
            @Value("${grpc.client.python-scraper.address:localhost}") String address,
            @Value("${grpc.client.python-scraper.port:50051}") int port
    ) {
        log.info("Connecting to gRPC server at {}:{}", address, port);

        this.channel = ManagedChannelBuilder.forAddress(address, port)
                .usePlaintext() // Use plaintext for simplicity, consider TLS in production
                .build();

        // Initialize both stubs if you need both sync and async calls
        this.blockingStub = SourceServiceGrpc.newBlockingStub(channel);
        this.futureStub = SourceServiceGrpc.newFutureStub(channel);
    }

    /**
     * Calls the addSource gRPC method on the Python service synchronously.
     * (Assuming you still want addSource to be blocking)
     *
     * @param url The URL of the source to add.
     * @param containsAiContent Whether the source may contain AI content.
     * @param containsAfricaContent Whether the source may contain Africa content.
     * @return The response from the gRPC service.
     */
    public SourceProto.SourceResponse addSource(String url, boolean containsAiContent, boolean containsAfricaContent) {
        log.info("Calling addSource gRPC method for URL: {}", url);
        SourceProto.SourceRequest request = SourceProto.SourceRequest.newBuilder()
                .setUrl(url)
                .setContainsAiContent(containsAiContent)
                .setContainsAfricaContent(containsAfricaContent)
                .build();
        try {
            SourceProto.SourceResponse response = blockingStub.addSource(request); // Use blocking stub
            log.info("Received response from addSource: {}", response.getMessage());
            return response;
        } catch (Exception e) {
            log.error("gRPC call to addSource failed for URL {}: {}", url, e.getMessage(), e);
            return SourceProto.SourceResponse.newBuilder()
                    .setMessage("Error calling addSource: " + e.getMessage())
                    .build();
        }
    }

    /**
     * Calls the scrape gRPC method on the Python service asynchronously ("fire-and-forget").
     * This method returns immediately.
     */
    public void initiateScrape() {
        log.info("Initiating asynchronous scrape gRPC call.");
        SourceProto.ScrapeRequest request = SourceProto.ScrapeRequest.newBuilder().build();
        try {
            // Use futureStub - this call returns immediately
            ListenableFuture<SourceProto.ScrapeResponse> futureResponse = futureStub.scrape(request);

            // You *could* add a callback to the future if you wanted to log
            // completion or errors asynchronously, but you don't wait for it here.
            // Example (optional):
            // futureResponse.addListener(() -> {
            //     try {
            //         SourceProto.ScrapeResponse response = futureResponse.get();
            //         log.info("Asynchronous scrape completed: {}", response.getMessage());
            //     } catch (Exception e) {
            //         log.error("Asynchronous scrape failed: {}", e.getMessage(), e);
            //     }
            // }, MoreExecutors.directExecutor()); // Or use a proper executor

            log.info("Asynchronous scrape call initiated.");
        } catch (Exception e) {
            // Catch potential immediate errors during call initiation (rare)
            log.error("Failed to initiate asynchronous gRPC call to scrape: {}", e.getMessage(), e);
            // Depending on requirements, you might want to re-throw or handle this
        }
    }

   // Consider adding a shutdown method for the channel when the application stops
   // For example, using @PreDestroy
   // @PreDestroy
   // public void shutdown() {
   //    log.info("Shutting down gRPC channel.");
   //    channel.shutdown();
   // }
}