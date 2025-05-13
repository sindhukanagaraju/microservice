//package com.microservice.common_service.config;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.reactive.function.client.WebClient;
//
//@Configuration
//public class WebClientFactory implements WebClientFactoryInterface {
//
//    @Value("${productservice.base.url}")
//    private String productServiceUrl;
//    private String shroomServiceUrl;
//
//    private final WebClient.Builder webClientBuilder;
//
//    public WebClientFactory(WebClient.Builder webClientBuilder) {
//        this.webClientBuilder = webClientBuilder;
//    }
//
//    @Bean
//    public ModelMapper modelMapperBean() {
//        return new ModelMapper();
//    }
//
//    @Override
//    public WebClient getWebClient(String baseUrl) {
//        return webClientBuilder.baseUrl(baseUrl).build();
//    }
//    public WebClient getProductWebClient() {
//        return getWebClient(productServiceUrl);
//    }
//}

package com.microservice.common_service.config;


import com.microservice.common_service.config.WebClientFactoryInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientFactory implements WebClientFactoryInterface {
    private final WebClient.Builder webClientBuilder;
    private final String showroomServiceUrl;
    private final String productServiceUrl;

    public WebClientFactory(WebClient.Builder webClientBuilder,
                            @Value("${showroom.service.url}") String showroomServiceUrl,
                            @Value("${product.service.url}")String productServiceUrl) {
        this.webClientBuilder = webClientBuilder;
        this.showroomServiceUrl = showroomServiceUrl;
        this.productServiceUrl = productServiceUrl;
    }

    @Override
    public WebClient getWebClient(String baseUrl) {
        return webClientBuilder.baseUrl(baseUrl).build();
    }
    public WebClient getproductWebClient(){
        return getWebClient(productServiceUrl);
    }
    public WebClient getShowroomWebClient(){
        return getWebClient(showroomServiceUrl);
    }
}