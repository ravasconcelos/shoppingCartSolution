package com.vasconcelos.catalog.business.service;

import java.util.List;
import java.util.Optional;

import com.vasconcelos.catalog.business.domain.ProductModel;

/**
 * @author Rodolfo Vasconcelos.
 */
public interface ProductService {

    public List<ProductModel> getAllProducts();

    public Optional<ProductModel> getProduct(Long id);
}
