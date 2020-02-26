package com.vasconcelos.shoppingcart.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vasconcelos.shoppingcart.business.domain.ProductModel;
import com.vasconcelos.shoppingcart.business.service.CatalogService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rodolfo Vasconcelos.
 */
@Slf4j
@RestController
@RequestMapping("/products")
public class ProductServicesController {

    private CatalogService catalogService;

    @Autowired
    public ProductServicesController(CatalogService catalogService) {
        super();
        this.catalogService = catalogService;
    }

    @GetMapping()
    public List<ProductModel> getAllProducts() {
        log.trace("Starting getAllProducts");

        return catalogService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductModel getProduct(@PathVariable Long id) {
        log.trace("Starting getProduct " + id);

        return catalogService.getProduct(id);
    }

}
