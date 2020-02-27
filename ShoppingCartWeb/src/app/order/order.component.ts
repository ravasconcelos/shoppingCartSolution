import { Component, OnInit, AfterViewInit } from '@angular/core';
import { CartService } from '../cart.service';
import { Cart } from '../cart';
import { Product } from '../product';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit, AfterViewInit {

  items;
  orderId;
  productMap: Map<string, Product>;
  cart: Cart;

  constructor(
    private cartService: CartService
  ) { 
    
  }

  ngOnInit() {
    this.items = this.cartService.getItems();
    this.productMap = this.cartService.getProductMap();
    this.cart = this.cartService.getShoppingCart();
    this.orderId = this.cart.orderId;
    console.log("this.cart:"+ this.cart);
    console.log("this.orderId:"+ this.orderId);
  }

  ngAfterViewInit() {
      console.log("ngAfterViewInit");
      this.cartService.clearCart();
  }

}