// 代码生成时间: 2025-09-23 20:43:44
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.runtime.server.EmbeddedServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.inject.Singleton;
import java.net.URI;
import java.net.http.HttpClient as JavaHttpClient;
import java.net.http.HttpRequest as JavaHttpRequest;
import java.net.http.HttpResponse as JavaHttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// 定义UrlValidatorService类，用于URL链接有效性验证
@Singleton
public class UrlValidatorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UrlValidatorService.class);
    private final HttpClient httpClient;

    // 构造函数注入HttpClient
    public UrlValidatorService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    // 验证URL链接有效性的方法
    public boolean isValidUrl(String url) {
        try {
            // 使用JavaHttpClient发送HEAD请求验证URL
            JavaHttpClient javaHttpClient = JavaHttpClient.newBuilder()
                    .version(JavaHttpClient.Version.HTTP_1_1)
                    .connectTimeout(Duration.ofSeconds(10))
                    .build();
            JavaHttpRequest javaHttpRequest = JavaHttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .method("HEAD", java.net.http.HttpRequest.BodyPublishers.noBody())
                    .build();

            // 异步发送请求并获取响应
            CompletableFuture<JavaHttpResponse<java.nio.ByteBuffer>> responseFuture = javaHttpClient.sendAsync(javaHttpRequest, java.net.http.HttpResponse.BodyHandlers.ofByteArray());
            JavaHttpResponse<java.nio.ByteBuffer> response = responseFuture.get();

            // 检查响应状态码，200为有效链接
            int statusCode = response.statusCode();
            return statusCode == 200;
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error("Error validating URL: {}", url, e);
        } catch (HttpClientResponseException e) {
            LOGGER.error("HTTP client error validating URL: {}", url, e);
        } catch (Exception e) {
            LOGGER.error("Unexpected error validating URL: {}", url, e);
        }

        return false;
    }
}
