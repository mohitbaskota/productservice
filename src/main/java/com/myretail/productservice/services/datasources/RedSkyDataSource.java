package com.myretail.productservice.services.datasources;

import com.myretail.productservice.models.Product;
import com.myretail.productservice.models.Redsky.ProductDetailsResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class RedSkyDataSource implements ProductDataSource {

    WebClient client;

    public RedSkyDataSource(WebClient client) {
        this.client = client;
    }

    private final static String productPath = "/redsky_aggregations/v1/redsky/case_study_v1";

    @Override
    public Product getProductDetailsById(Long productId) {


        client.get()
                .uri(uriBuilder -> uriBuilder
                        .path(productPath)
                        .queryParam("tcin", productId)
                        .queryParam("key", "")
                        .build()
                )
                .retrieve()
                .bodyToMono(ProductDetailsResponse.class)
                .block();
        return null;
    }
}
