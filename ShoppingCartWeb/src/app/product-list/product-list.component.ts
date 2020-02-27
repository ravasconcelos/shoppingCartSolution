import { Component } from '@angular/core';

import { ProductService } from '../product.service';
import { CartService } from '../cart.service';
import { CartItem } from '../cartItem';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent {
  products = [];

  constructor(
    private cartService: CartService,
    private productService: ProductService
  ) { }

  ngOnInit() {
    
    this.productService.getProducts().subscribe(resp => {
      console.log(resp);
      const keys = resp.headers.keys();
      console.log(keys);
  
      for (const data of resp.body) {
        console.log(data);
        this.products.push(data);
      }
      
    });
  }

  buy(product) {
    product.quantity = 1;
    this.addToCart(product);
  }

  onNotify() {
    window.alert('You will be notified when the product goes on sale');
  }  

  addToCart(product) {

    var cartItem: CartItem = new CartItem();
    cartItem.cartId = +this.cartService.getStoredCartId();
    cartItem.productId = product.id;
    cartItem.price = product.price;
    cartItem.quantity = product.quantity;

    console.log('CartService.addToCart will call createCartItem');
    this.cartService.createCartItem(cartItem).subscribe(resp => {
      console.log(resp);
      const keys = resp.headers.keys();
      console.log(keys);
      console.log(resp.body);
      window.alert('Your product has been added to the cart!');
    });
    console.log('CartService.addToCart finished');
  }  
}


/*
Copyright Google LLC. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/