package com.vasconcelos.shoppingcart.business.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vasconcelos.shoppingcart.business.domain.CartItemModel;
import com.vasconcelos.shoppingcart.data.entity.CartItem;
import com.vasconcelos.shoppingcart.data.repository.CartItemRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rodolfo Vasconcelos.
 */
@Slf4j
@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        super();
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public Optional<CartItemModel> getCartItemById(Long cartItemId) {
        log.trace(String.format("getCartItemById cartItemId: %d", cartItemId));
        Optional<CartItem> cartItem = this.cartItemRepository.findById(cartItemId);
        Optional<CartItemModel> cartItemModel = Optional.empty();
        if (cartItem.isPresent()) {
            cartItemModel = Optional.of(CartItemModel.fromCartEntity(cartItem.get()));
        }
        return cartItemModel;
    }

    @Override
    public CartItemModel createOrUpdateCartItem(CartItemModel cartItemModel) {
        log.trace(String.format("createOrUpdateCart: %s", cartItemModel.toString()));
        CartItem cartItem = this.cartItemRepository.save(cartItemModel.toCartItemEntity());
        return CartItemModel.fromCartEntity(cartItem);
    }

    @Override
    public void deleteCartItem(CartItemModel cartItemModel) {
        log.trace(String.format("deleteCartItem: %s", cartItemModel.toString()));
        this.cartItemRepository.delete(cartItemModel.toCartItemEntity());
        log.trace(String.format("CartItem %d is deleted", cartItemModel.getId()));
    }

}
