package com.myretail.productservice.services.externalsourceservices;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myretail.productservice.config.AppConfig;
import com.myretail.productservice.dto.Product;
import com.myretail.productservice.dto.Redsky.*;
import com.myretail.productservice.exceptions.ServiceException;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class RedSkyDataSourceServiceTest {

    public static MockWebServer mockWebServer;

    private RedSkyDataSourceService redSkyDataSourceService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private AppConfig appConfig;

    @BeforeAll
    static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @BeforeEach
    void initialize() {
        String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
        redSkyDataSourceService = new RedSkyDataSourceService(WebClient.create(baseUrl), appConfig);
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();

    }

    @Test
    void testValidProductIsReturnedOnValidResponse() throws Exception {

        Item item = new Item();
        ProductDescription productDescription = new ProductDescription();
        productDescription.setTitle("Test Title");
        item.setProductDescription(productDescription);

        RedSkyProduct redSkyProduct = new RedSkyProduct();
        redSkyProduct.setTcin("123");
        redSkyProduct.setItem(item);
        Data data = new Data();
        data.setProduct(redSkyProduct);

        ProductDetailsResponse productDetailsResponse = new ProductDetailsResponse();
        productDetailsResponse.setData(data);

        mockWebServer.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(productDetailsResponse))
                .addHeader("Content-Type", "application/json"));

        Product expectedProduct = new Product();
        expectedProduct.setId(123L);
        expectedProduct.setName("Test Title");
        expectedProduct.setCurrentPrice(null);

        Product actualProduct = redSkyDataSourceService.getProductDetailsById(123L);
        assertThat(actualProduct.getId()).isEqualTo(expectedProduct.getId());
        assertThat(actualProduct.getName()).isEqualTo(expectedProduct.getName());

    }

    @Test
    void testEmptyProductIsReturnedOnEmptyResponse() throws Exception {

        mockWebServer.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(null))
                .addHeader("Content-Type", "application/json"));

        Product expectedProduct = new Product();
        Product actualProduct = redSkyDataSourceService.getProductDetailsById(123L);
        assertThat(actualProduct.getId()).isEqualTo(expectedProduct.getId());
        assertThat(actualProduct.getName()).isEqualTo(expectedProduct.getName());

    }


    @Test
    void testExceptionIsThrownOnServerError() throws Exception {

        mockWebServer.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(null))
                .addHeader("Content-Type", "application/json")
                .setResponseCode(500));

        assertThatThrownBy(() -> {
            redSkyDataSourceService.getProductDetailsById(123L);
        }).isInstanceOf(ServiceException.class);

    }


}