package com.microservice.common_service.config;

import org.springframework.web.reactive.function.client.WebClient;

public interface WebClientFactoryInterface {
    WebClient getWebClient(String baseUrl);
}
