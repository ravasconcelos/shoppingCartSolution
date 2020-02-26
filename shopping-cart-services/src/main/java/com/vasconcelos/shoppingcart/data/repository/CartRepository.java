package com.vasconcelos.shoppingcart.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vasconcelos.shoppingcart.data.entity.Cart;

/**
 * @author Rodolfo.
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
