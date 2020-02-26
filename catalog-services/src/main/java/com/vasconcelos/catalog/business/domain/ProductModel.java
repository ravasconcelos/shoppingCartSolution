package com.vasconcelos.catalog.business.domain;

import java.math.BigDecimal;

import com.vasconcelos.catalog.data.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Rodolfo Vasconcelos.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;

    public static ProductModel fromProductEntity(Product product) {
        return ProductModel.builder().id(product.getId()).name(product.getName()).description(product.getDescription())
                .price(product.getPrice()).imageUrl(product.getImageUrl()).build();
    }
}
