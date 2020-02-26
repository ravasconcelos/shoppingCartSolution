package com.vasconcelos.shoppingcart.business.domain;

import java.math.BigDecimal;

import com.vasconcelos.shoppingcart.data.entity.Cart;
import com.vasconcelos.shoppingcart.data.entity.CartItem;

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
public class CartItemModel {

    private Long id;
    private Long cartId;
    private Long productId;
    private BigDecimal quantity;
    private BigDecimal price;

    public CartItem toCartItemEntity() {
        CartItem cartItem = CartItem.builder().id(id).cart(Cart.builder().id(cartId).build()).productId(productId)
                .quantity(quantity).price(price).build();
        return cartItem;
    }

    public static CartItemModel fromCartEntity(CartItem cartItem) {
        return CartItemModel.builder().id(cartItem.getId()).cartId(cartItem.getCart().getId())
                .productId(cartItem.getProductId()).quantity(cartItem.getQuantity()).price(cartItem.getPrice()).build();
    }

}
