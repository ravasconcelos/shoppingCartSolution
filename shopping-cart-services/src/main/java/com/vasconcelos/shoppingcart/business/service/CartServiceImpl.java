package com.vasconcelos.shoppingcart.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vasconcelos.shoppingcart.business.domain.CartItemModel;
import com.vasconcelos.shoppingcart.business.domain.CartModel;
import com.vasconcelos.shoppingcart.data.entity.Cart;
import com.vasconcelos.shoppingcart.data.entity.CartItem;
import com.vasconcelos.shoppingcart.data.repository.CartRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rodolfo Vasconcelos.
 */
@Slf4j
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        super();
        this.cartRepository = cartRepository;
    }

    @Override
    public List<CartModel> getAllCarts() {
        log.trace("getAllCarts");
        List<Cart> carts = this.cartRepository.findAll();

        List<CartModel> cartModels = new ArrayList<CartModel>();
        carts.forEach(cart -> {
            CartModel cartModel = CartModel.fromCartEntity(cart);
            List<CartItem> cartItems = cart.getItems();
            List<CartItemModel> cartItemModels = new ArrayList<>();
            if (cartItems != null) {

                cartItems.forEach(cartItem -> {
                    cartItemModels.add(CartItemModel.fromCartEntity(cartItem));
                });
            }
            cartModel.setItems(cartItemModels);

            cartModels.add(cartModel);
        });

        return cartModels;
    }

    @Override
    public Optional<CartModel> getCart(Long id) {
        log.trace("getCart id: " + id);
        Optional<Cart> cart = this.cartRepository.findById(id);
        Optional<CartModel> cartModel = Optional.empty();
        if (cart.isPresent()) {
            cartModel = Optional.of(CartModel.fromCartEntity(cart.get()));

            List<CartItem> cartItems = cart.get().getItems();
            List<CartItemModel> cartItemModels = new ArrayList<>();
            if (cartItems != null) {

                cartItems.forEach(cartItem -> {
                    cartItemModels.add(CartItemModel.fromCartEntity(cartItem));
                });
            }
            cartModel.get().setItems(cartItemModels);
        }
        return cartModel;
    }

    @Override
    public CartModel createOrUpdateCart(CartModel cartModel) {
        log.trace("createOrUpdateCart: " + cartModel);
        Cart cart = this.cartRepository.save(cartModel.toCartEntity());

        cartModel = CartModel.fromCartEntity(cart);

        List<CartItem> cartItems = cart.getItems();
        List<CartItemModel> cartItemModels = new ArrayList<>();
        if (cartItems != null) {

            cartItems.forEach(cartItem -> {
                cartItemModels.add(CartItemModel.fromCartEntity(cartItem));
            });
        }
        cartModel.setItems(cartItemModels);

        return cartModel;
    }

    @Override
    public void deleteCart(CartModel cart) {
        log.trace("deleteCart: " + cart);
        this.cartRepository.delete(cart.toCartEntity());
        log.trace(String.format("Cart %d is deleted", cart.getId()));
    }
}
