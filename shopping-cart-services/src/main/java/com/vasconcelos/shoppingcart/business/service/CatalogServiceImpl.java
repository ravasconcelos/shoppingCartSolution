package com.vasconcelos.shoppingcart.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vasconcelos.shoppingcart.business.domain.ProductModel;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rodolfo Vasconcelos.
 */
@Slf4j
@Service
public class CatalogServiceImpl implements CatalogService {

    private static final String PRODUCTS = "/products";
    private static final String SLASH = "/";

    @Value("${catalog.service.url}")
    private String catalogServiceUrl;

    private final RestTemplate restTemplate;

    @Autowired
    public CatalogServiceImpl(RestTemplate restTemplate) {
        super();
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ProductModel> getAllProducts() {
        log.trace("Starting getAllProducts");

        String url = catalogServiceUrl + PRODUCTS;

        log.debug("Fetching the producs from " + url);

        HttpEntity<String> request = new HttpEntity<>(null, null);
        return this.restTemplate
                .exchange(url, HttpMethod.GET, request, new ParameterizedTypeReference<List<ProductModel>>() {
                }).getBody();
    }

    @Override
    public ProductModel getProduct(Long id) {
        log.trace("Starting getProduct");

        String url = catalogServiceUrl + PRODUCTS + SLASH + id;

        log.debug("Fetching the producs from " + url);

        HttpEntity<String> request = new HttpEntity<>(null, null);
        return this.restTemplate.exchange(url, HttpMethod.GET, request, new ParameterizedTypeReference<ProductModel>() {
        }).getBody();
    }

}
