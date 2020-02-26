package com.vasconcelos.shoppingcart.business.service;

import java.util.List;

import com.vasconcelos.shoppingcart.business.domain.ProductModel;

/**
 * @author Rodolfo Vasconcelos.
 */
public interface CatalogService {

    public List<ProductModel> getAllProducts();

    public ProductModel getProduct(Long id);

}
