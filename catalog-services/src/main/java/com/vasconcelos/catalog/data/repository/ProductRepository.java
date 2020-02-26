package com.vasconcelos.catalog.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vasconcelos.catalog.data.entity.Product;

/**
 * @author Rodolfo Vasconcelos.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
