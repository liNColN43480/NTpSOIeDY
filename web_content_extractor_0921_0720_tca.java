// 代码生成时间: 2025-09-21 07:20:19
package com.example.webcontentextractor;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.Micronaut;
import io.reactivex.Single;
import javax.inject.Singleton;

@Singleton
public class WebContentExtractor {
    // Client responsible for making HTTP requests
    @Client("/")
    private RxHttpClient client;

    public Single<String> fetchWebContent(String url) {
        try {
            // Performing the HTTP GET request and extracting the body content
            return client.toBlocking().retrieve(HttpRequest.GET(url), String.class);
        } catch (Exception e) {
            // Handling any exceptions that may occur during the HTTP request
            throw new RuntimeException("Failed to fetch content from: " + url, e);
        }
    }
}

/**
 * Main class to run the Micronaut application.
 * It provides a simple way to execute the web content extraction.
 */
package com.example.webcontentextractor;

import io.micronaut.runtime.Micronaut;

public class Application {
    public static void main(String[] args) {
        Micronaut.run(Application.class);
    }
}

/**
 * A Micronaut configuration class to define any necessary configurations.
 * This can include settings for HTTP client, timeouts, etc.
 */
package com.example.webcontentextractor;

import io.micronaut.context.annotation.ConfigurationBuilder;
import io.micronaut.context.annotation.ConfigurationProperties;
import javax.net.ssl.SSLContext;

@ConfigurationProperties("net.http")
public class HttpClientConfig {
    private final HttpClientProperties client;

    public HttpClientConfig(HttpClientProperties client) {
        this.client = client;
    }

    public HttpClientProperties getClient() {
        return client;
        public ConfigurationBuilder<SSLContext> sslContext() {
            return client.getSslContext();
        }
    }
}

/**
 * Inner class to hold HTTP client properties.
 */
package com.example.webcontentextractor;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.client.ssl.HttpClientSslConfiguration;

public class HttpClientProperties {
    private HttpClientSslConfiguration ssl;

    public HttpClientSslConfiguration getSsl() {
        return ssl;
    }

    public void setSsl(HttpClientSslConfiguration ssl) {
        this.ssl = ssl;
    }
}
