import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { CartService } from '../cart.service';
import { Cart } from '../cart';
import { Product } from '../product';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  items;
  productMap: Map<string, Product>;
  cart: Cart;
  mySubscription: any;

  constructor(
    private cartService: CartService,
    private router: Router
  ) {   

  }

  ngOnInit() {
    this.items = this.cartService.getItems();
    this.productMap = this.cartService.getProductMap();
    this.cart = this.cartService.getShoppingCart();
  }

  ngOnDestroy() {
    if (this.mySubscription) {
      this.mySubscription.unsubscribe();
    }
  }
  
  onSubmit(customerData) {
    // Process checkout data here
    this.items = this.cartService.clearCart();
    console.warn('Your order has been submitted', customerData);
  }

  removeFromCart(item) {
    this.cartService.removeFromCart(item);
    window.alert('Your product has been removed from the cart!');
  }

  updateCartItem(item, event) {
    item.quantity = event.target.value;
    console.log("item.quantity: "+item.quantity);
    this.cartService.updateQuantity(item);
    window.alert('Your product has been updated in the cart!');
  }

  async delay(ms: number) {
    await new Promise(resolve => setTimeout(()=>resolve(), ms)).then(()=>console.log("fired"));
  }

}