package com.vasconcelos.shoppingcart.web;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vasconcelos.shoppingcart.business.domain.CartItemModel;
import com.vasconcelos.shoppingcart.business.domain.CartModel;
import com.vasconcelos.shoppingcart.business.service.CartItemService;
import com.vasconcelos.shoppingcart.business.service.CartService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rodolfo Vasconcelos.
 */
@Slf4j
@RestController
@RequestMapping("/carts")
public class ShoppingCartServicesController {

    private CartService cartService;
    private CartItemService cartItemService;

    @Autowired
    public ShoppingCartServicesController(CartService cartService, CartItemService cartItemService) {
        super();
        this.cartService = cartService;
        this.cartItemService = cartItemService;
    }

    @GetMapping()
    public List<CartModel> getAllCarts() {
        log.trace("Starting getAllCarts");

        return cartService.getAllCarts();
    }

    @PostMapping
    public ResponseEntity<CartModel> createCart() {
        CartModel cartModel = CartModel.builder().status("Open").build();
        cartModel.setItems(new ArrayList<CartItemModel>());
        CartModel cart = this.cartService.createOrUpdateCart(cartModel);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cart.getId())
                .toUri();
        return ResponseEntity.created(location).body(cart);
    }

    @GetMapping("/{id}")
    public CartModel getCart(@PathVariable Long id) {
        log.trace("Starting getCart " + id);

        Optional<CartModel> cart = cartService.getCart(id);

        return cart.orElseThrow(() -> new RuntimeException("Cart " + id + " does not exist"));
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long cartId) {
        log.trace("Starting deleteCart " + cartId);

        Optional<CartModel> cartModel = cartService.getCart(cartId);

        if (!cartModel.isPresent()) {
            throw new RuntimeException("Cart " + cartId + " does not exist");
        }

        List<CartItemModel> cartItemModels = cartModel.get().getItems();
        if (cartItemModels != null) {
            cartItemModels.forEach(cartItemModel -> this.cartItemService.deleteCartItem(cartItemModel));
        }

        this.cartService.deleteCart(cartModel.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartModel> addCartItem(@PathVariable Long cartId, @RequestBody CartItemModel cartItemModel) {

        Optional<CartModel> cartModelOptional = cartService.getCart(cartId);
        if (!cartModelOptional.isPresent()) {
            throw new RuntimeException("Cart " + cartId + " does not exist");
        }

        CartModel cartModel = cartModelOptional.get();

        cartItemModel.setCartId(cartId);
        List<CartItemModel> cartItemModels = cartModel.getItems();
        if (cartItemModels == null) {
            cartItemModels = new ArrayList<CartItemModel>();
            cartModel.setItems(cartItemModels);
        }
        cartItemModels.add(cartItemModel);

        CartItemModel savedCartItemModel = this.cartItemService.createOrUpdateCartItem(cartItemModel);
        cartItemModel.setId(savedCartItemModel.getId());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cartModel).toUri();
        return ResponseEntity.created(location).body(cartModel);
    }

    @PutMapping("/{cartId}/items/{cartItemId}")
    public ResponseEntity<CartModel> updateCartItem(@PathVariable Long cartId, @PathVariable Long cartItemId,
            @RequestBody CartItemModel cartItemModel) {

        Optional<CartModel> cartModel = cartService.getCart(cartId);
        if (!cartModel.isPresent()) {
            throw new RuntimeException("Cart " + cartId + " does not exist");
        }

        cartItemModel.setCartId(cartId);
        cartItemModel = this.cartItemService.createOrUpdateCartItem(cartItemModel);

        cartItemModel.setCartId(cartId);
        List<CartItemModel> cartItemModels = cartModel.get().getItems();
        Optional<CartItemModel> oldCartItemModel = cartItemModels.stream().filter(item -> item.getId() == cartItemId)
                .findFirst();
        if (oldCartItemModel.isPresent()) {
            oldCartItemModel.get().setPrice(cartItemModel.getPrice());
            oldCartItemModel.get().setQuantity(cartItemModel.getQuantity());
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cartModel.get())
                .toUri();
        return ResponseEntity.created(location).body(cartModel.get());
    }

    @DeleteMapping("/{cartId}/items/{cartItemId}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long cartId, @PathVariable Long cartItemId) {

        Optional<CartModel> cartModel = cartService.getCart(cartId);
        if (!cartModel.isPresent()) {
            throw new RuntimeException("Cart " + cartId + " does not exist");
        }

        Optional<CartItemModel> cartItemModel = this.cartItemService.getCartItemById(cartItemId);
        if (!cartItemModel.isPresent()) {
            throw new RuntimeException("CartItem " + cartItemId + " does not exist");
        }

        List<CartItemModel> cartItemModels = cartModel.get().getItems();
        if (cartItemModels != null) {
            cartItemModels.removeIf(item -> item.getId() == cartItemId);
            this.cartItemService.createOrUpdateCartItem(cartItemModel.get());
        }

        this.cartItemService.deleteCartItem(cartItemModel.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{cartId}/submit")
    public ResponseEntity<Void> submitCart(@PathVariable Long cartId) {

        Optional<CartModel> cartModel = cartService.getCart(cartId);
        if (!cartModel.isPresent()) {
            throw new RuntimeException("Cart " + cartId + " does not exist");
        }

        cartModel.get().setStatus("Submitted");
        cartModel.get().setOrderId(new Long(new Random().nextInt(9999999)));

        cartService.createOrUpdateCart(cartModel.get());

        return ResponseEntity.noContent().build();
    }

}
