import { Component, OnInit, AfterViewInit } from '@angular/core';
import { CartService } from '../cart.service';
import { ProductService } from '../product.service';
import { Cart } from '../cart';
import { Product } from '../product';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit, AfterViewInit {

  orderId: number;
  productMap: Map<string, Product>;
  cart: Cart;

  constructor(
    private cartService: CartService,
    private productService: ProductService
  ) { 
    
  }

  ngOnInit() {
    
    this.productMap = this.productService.getProductMap();
    this.getShoppingCart();
    console.log("this.cart:"+ this.cart);
    console.log("this.orderId:"+ this.orderId);
  }

  ngAfterViewInit() {
      console.log("ngAfterViewInit");
      this.cartService.clearCart();
  }
  getShoppingCart() {
    console.log('CartService.getShoppingCart');
    this.cartService.getCart(+this.cartService.getStoredCartId()).subscribe(resp => {
      console.log(resp);
      const keys = resp.headers.keys();
      console.log(keys);
      this.cart = resp.body;
      this.orderId = this.cart.orderId;      
      console.log(resp.body);
    });
    console.log('CartService.getShoppingCart finished');
    return this.cart;
  }
}