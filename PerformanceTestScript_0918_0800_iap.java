// 代码生成时间: 2025-09-18 08:00:56
import io.micronaut.context.ApplicationContext;
import io.micronaut.discovery.ServiceInstance;
import io.micronaut.discovery.ServiceInstanceList;
import io.micronaut.discovery.services.DefaultServiceInstance;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.server.EmbeddedServer;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * PerformanceTestScript class is designed to perform performance testing on a Micronaut service.
 */
@Singleton
public class PerformanceTestScript {

    @Inject
    @Client("/")
    RxHttpClient client;

    @Inject
    EmbeddedServer server;

    /**
     * Performs a performance test on the service by sending a specified number of requests.
     *
     * @param numberOfRequests The number of requests to send.
     * @param delayInMillis The delay between requests in milliseconds.
     * @return A list of response times in milliseconds.
     */
    public List<Long> performTest(int numberOfRequests, long delayInMillis) {
        List<Long> responseTimes = new ArrayList<>();
        for (int i = 0; i < numberOfRequests; i++) {
            long startTime = System.nanoTime();
            try {
                // Send a request to the server and receive a response
                HttpResponse<String> response = client.exchange(HttpRequest.GET("/"), String.class).blockingFirst();
                if (response.code() == 200) {
                    long endTime = System.nanoTime();
                    long responseTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
                    responseTimes.add(responseTime);
                } else {
                    throw new RuntimeException("Received non-200 status code: " + response.code());
                }
            } catch (Exception e) {
                throw new RuntimeException("Error performing test request: " + e.getMessage(), e);
            } finally {
                try {
                    // Wait for the specified delay before sending the next request
                    TimeUnit.MILLISECONDS.sleep(delayInMillis);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Test interrupted: " + e.getMessage(), e);
                }
            }
        }
        return responseTimes;
    }

    /**
     * Starts the Micronaut server for the performance test.
     */
    public void startServer() {
        // Start the server in a separate thread to keep the main thread free for testing
        new Thread(() -> server.start()).start();
    }

    /**
     * Stops the Micronaut server after the performance test is complete.
     */
    public void stopServer() {
        server.stop();
    }
}
