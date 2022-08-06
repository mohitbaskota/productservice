package com.myretail.productservice.services.externalsourceservices;

import com.myretail.productservice.config.AppConfig;
import com.myretail.productservice.exceptions.ServiceException;
import com.myretail.productservice.dto.Product;
import com.myretail.productservice.dto.Redsky.ProductDetailsResponse;
import com.myretail.productservice.dto.Redsky.RedSkyProduct;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class RedSkyDataSourceService implements ProductDataSourceService {

    private final WebClient client;
    private final AppConfig appConfig;

    public RedSkyDataSourceService(WebClient client,
                                   AppConfig appConfig) {
        this.client = client;
        this.appConfig = appConfig;
    }

    private final static String productPath = "/redsky_aggregations/v1/redsky/case_study_v1";

    @Override
    public Product getProductDetailsById(Long productId) throws ServiceException{

        ProductDetailsResponse resp = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path(productPath)
                        .queryParam("tcin", productId)
                        .queryParam("key", appConfig.getRedskyKey())
                        .build()
                )
                .retrieve()
                .onStatus(status -> status.value() != HttpStatus.OK.value(),
                        response -> Mono.error(new ServiceException(response.toString(), response.statusCode().value())))
                .bodyToMono(ProductDetailsResponse.class)
                .block();

        Product product = new Product();
        if (resp != null && resp.getData() != null && resp.getData().getProduct() != null) {
            RedSkyProduct redSkyProduct = resp.getData().getProduct();
            if (redSkyProduct.getItem() != null && redSkyProduct.getItem().getProductDescription() != null) {
                product.setName(redSkyProduct.getItem().getProductDescription().getTitle());
                product.setId(productId);
            }
        }
        return product;
    }
}
