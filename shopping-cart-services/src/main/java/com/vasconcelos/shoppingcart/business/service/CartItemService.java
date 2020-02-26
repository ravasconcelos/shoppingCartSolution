package com.vasconcelos.shoppingcart.business.service;

import java.util.Optional;

import com.vasconcelos.shoppingcart.business.domain.CartItemModel;

/**
 * @author Rodolfo Vasconcelos.
 */
public interface CartItemService {

    public Optional<CartItemModel> getCartItemById(Long cartItemId);

    public CartItemModel createOrUpdateCartItem(CartItemModel cartItemModel);

    public void deleteCartItem(CartItemModel cartItemModel);

}
