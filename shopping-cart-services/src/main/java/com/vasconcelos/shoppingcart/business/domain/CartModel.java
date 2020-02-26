package com.vasconcelos.shoppingcart.business.domain;

import java.util.List;

import com.vasconcelos.shoppingcart.data.entity.Cart;

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
public class CartModel {

    private Long id;
    private String status;
    private List<CartItemModel> items;

    public Cart toCartEntity() {
        Cart cart = Cart.builder().id(id).status(status).build();
        return cart;
    }

    public static CartModel fromCartEntity(Cart cart) {
        return CartModel.builder().id(cart.getId()).status(cart.getStatus()).build();
    }

}
