import { Component, OnInit, OnChanges } from '@angular/core';
import { CartService } from '../cart.service';
import { ProductService } from '../product.service';
import { Cart } from '../cart';
import { Product } from '../product';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit, OnChanges {

  productMap: Map<string, Product>;
  cart: Cart;
  mySubscription: any;

  constructor(
    private cartService: CartService,
    private productService: ProductService,
  ) {   

  }

  ngOnChanges() {
    this.getShoppingCart();
  }

  ngOnInit() {
    this.productMap = this.productService.getProductMap();
    this.getShoppingCart();
  }

  removeFromCart(item) {
    this.cartService.removeFromCart(item);
    window.alert('Your product has been removed from the cart!');
    this.getShoppingCart();
  }

  updateCartItem(item, event) {
    item.quantity = event.target.value;
    console.log("item.quantity: "+item.quantity);
    this.cartService.updateQuantity(item);
    window.alert('Your product has been updated in the cart!');
    this.getShoppingCart();
  }

  getShoppingCart() {
    console.log('CartService.getShoppingCart');
    this.cartService.getCart(+this.cartService.getStoredCartId()).subscribe(resp => {
      console.log(resp);
      const keys = resp.headers.keys();
      console.log(keys);
      this.cart = resp.body;
      console.log(resp.body);
    });
    console.log('CartService.getShoppingCart finished');
    return this.cart;
  }
}