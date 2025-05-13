package com.microservice.showroom.config;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class ModelMapperConfig {
//
//    @Bean
//    public ModelMapper modelMapper() {
//        return new ModelMapper();
//    }
//}
//


import com.microservice.common_service.config.WebClientFactory;
import com.microservice.common_service.dto.ResponseDTO;
import com.microservice.showroom.DTO.ProductResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.file.ProviderNotFoundException;

@Component
public class ModelMapperConfig {

    @Autowired
    private WebClientFactory webClientFactory;

    @Autowired
    private ModelMapper modelMapper;

    private WebClient webClient;

    @Autowired
    public void initWebClient() {
        this.webClient = webClientFactory.getproductWebClient();
    }

    public ProductResponse getById(Integer id) {
        if (id == null) {
            return null;
        }
        return webClient.get()
                .uri("/api/v1/product/{id}", id)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.empty())
                .bodyToMono(ResponseDTO.class)
                .onErrorResume(e -> Mono.empty())
                .blockOptional()
                .map(responseDTO -> modelMapper.map(responseDTO.data(), ProductResponse.class))
                .orElseThrow(() -> new ProviderNotFoundException("all not allow "));
    }
}