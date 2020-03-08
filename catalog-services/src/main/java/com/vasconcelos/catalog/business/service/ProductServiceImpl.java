package com.vasconcelos.catalog.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.vasconcelos.catalog.business.domain.ProductModel;
import com.vasconcelos.catalog.data.entity.Product;
import com.vasconcelos.catalog.data.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rodolfo Vasconcelos.
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        super();
        this.productRepository = productRepository;
    }

    @Override
    @Cacheable("products")
    public List<ProductModel> getAllProducts() {
        log.trace("getAllProducts");
        List<Product> products = this.productRepository.findAll();

        List<ProductModel> productModels = new ArrayList<ProductModel>();
        products.forEach(product -> {
            ProductModel productModel = ProductModel.fromProductEntity(product);
            productModels.add(productModel);
        });

        return productModels;
    }

    @Override
    @Cacheable("product")
    public Optional<ProductModel> getProduct(Long id) {
        log.trace("getProduct id:" + id);
        Optional<Product> product = this.productRepository.findById(id);

        Optional<ProductModel> productModel = Optional.empty();
        if (product.isPresent()) {
            productModel = Optional.of(ProductModel.fromProductEntity(product.get()));
        }

        return productModel;
    }

}
