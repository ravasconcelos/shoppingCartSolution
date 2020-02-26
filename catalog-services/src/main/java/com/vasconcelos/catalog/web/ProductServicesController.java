package com.vasconcelos.catalog.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vasconcelos.catalog.business.domain.ProductModel;
import com.vasconcelos.catalog.business.service.ProductService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rodolfo Vasconcelos.
 */
@Slf4j
@RestController
@RequestMapping("/products")
public class ProductServicesController {

    private ProductService productService;

    @Autowired
    public ProductServicesController(ProductService productService) {
        super();
        this.productService = productService;
    }

    @GetMapping
    public List<ProductModel> getAllProducts() {
        log.trace("Starting getAllProducts");

        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductModel getProduct(@PathVariable Long id) {
        log.trace("Starting getProduct");

        Optional<ProductModel> product = productService.getProduct(id);

        return product.orElseThrow(() -> new RuntimeException("Product " + id + " does not exist"));
    }

}
