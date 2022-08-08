package com.myretail.productservice.services;

import com.myretail.productservice.dto.Product;
import com.myretail.productservice.models.Price;
import com.myretail.productservice.models.ProductItem;
import com.myretail.productservice.services.databaseservices.MongoDBService;
import com.myretail.productservice.services.externalsourceservices.ProductDataSourceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductDataSourceService productDataSourceService;

    @Mock
    private MongoDBService databaseService;

    private ProductService productService;

    @BeforeEach
    public void init() {
        productService = new ProductService(productDataSourceService, databaseService);
    }

    @Test
    public void testProductIsReturnedWhenNotInDatabaseButInDataSource() {
        Product expectedProduct = new Product();
        expectedProduct.setName("Test Product");
        expectedProduct.setId(123L);
        when(productDataSourceService.getProductDetailsById(anyLong())).thenReturn(expectedProduct);
        when(databaseService.getProductById(anyLong())).thenReturn(null);
        Product actualProduct = productService.getProductById(anyLong());
        assertThat(actualProduct).isEqualTo(expectedProduct);
    }


    @Test
    public void testNullProductIdIsHandled() {
        when(productDataSourceService.getProductDetailsById(null)).thenReturn(new Product());
        when(databaseService.getProductById(null)).thenReturn(null);
        Product actualProduct = productService.getProductById(null);
        assertThat(actualProduct.getId()).isEqualTo(null);
        assertThat(actualProduct.getName()).isEqualTo(null);
    }

    @Test
    public void testDataSourceAndDatabaseResultIsMerged() {
        Product datasourceProduct = new Product();
        datasourceProduct.setName("Test Product");
        datasourceProduct.setId(123L);
        when(productDataSourceService.getProductDetailsById(anyLong())).thenReturn(datasourceProduct);

        ProductItem dbProduct = new ProductItem();
        dbProduct.setName("Test Product Database");
        dbProduct.setId(123456789L);
        Price price = new Price();
        price.setCurrency("USD");
        price.setValue(123);
        dbProduct.setCurrentPrice(price);

        when(databaseService.getProductById(anyLong())).thenReturn(dbProduct);
        Product actualProduct = productService.getProductById(anyLong());
        assertThat(actualProduct.getName()).isEqualTo(datasourceProduct.getName());
        assertThat(actualProduct.getId()).isEqualTo(datasourceProduct.getId());

        assertThat(actualProduct.getCurrentPrice().getCurrencyCode()).isEqualTo(dbProduct.getCurrentPrice().getCurrency());
        assertThat(actualProduct.getCurrentPrice().getValue()).isEqualTo(dbProduct.getCurrentPrice().getValue());
    }

}