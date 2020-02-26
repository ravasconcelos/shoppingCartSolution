package com.vasconcelos.catalog.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.vasconcelos.catalog.business.domain.ProductModel;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProductServicesControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetProductById() throws Exception {

        ResponseEntity<ProductModel> response = restTemplate
                .getForEntity("http://localhost:" + port + "/catalog-api/products/1", ProductModel.class);
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    public void testGetProducts() throws Exception {

        ResponseEntity<ProductModel[]> response = restTemplate
                .getForEntity("http://localhost:" + port + "/catalog-api/products/", ProductModel[].class);
        assertNotNull(response.getBody());
        assertEquals(7, response.getBody().length);
    }

}
