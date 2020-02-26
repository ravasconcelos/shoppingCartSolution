package com.vasconcelos.shoppingcart.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vasconcelos.shoppingcart.data.entity.CartItem;

/**
 * @author Rodolfo Vasconcelos.
 */
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
