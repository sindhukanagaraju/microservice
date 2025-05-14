package com.microservice.common_service.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientFactory implements WebClientFactoryInterface {

    @Value("${productservice.base.url}")
    private String productServiceUrl;

    private final WebClient.Builder webClientBuilder;

    public WebClientFactory(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Bean
    public ModelMapper modelMapperBean() {
        return new ModelMapper();
    }

    @Override
    public WebClient getWebClient(String baseUrl) {
        return webClientBuilder.baseUrl(baseUrl).build();
    }
    public WebClient getProductWebClient() {
        return getWebClient(productServiceUrl);
    }
}
