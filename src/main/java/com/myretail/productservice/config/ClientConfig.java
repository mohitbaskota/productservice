package com.myretail.productservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfig {

    private final AppConfig config;

    public ClientConfig(AppConfig config) {
        this.config = config;
    }

    @Bean
    public WebClient getRedSkyWebclient() {
        return WebClient.builder()
                .baseUrl(config.getRedskyUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
