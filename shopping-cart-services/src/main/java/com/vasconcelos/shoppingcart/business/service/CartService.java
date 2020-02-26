package com.vasconcelos.shoppingcart.business.service;

import java.util.List;
import java.util.Optional;

import com.vasconcelos.shoppingcart.business.domain.CartModel;

/**
 * @author Rodolfo Vasconcelos.
 */
public interface CartService {

    public List<CartModel> getAllCarts();

    public Optional<CartModel> getCart(Long id);

    public CartModel createOrUpdateCart(CartModel cartModel);

    public void deleteCart(CartModel cart);
}
