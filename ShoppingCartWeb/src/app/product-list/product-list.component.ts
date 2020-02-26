import { Component } from '@angular/core';

import { ProductService } from '../product.service';
import { CartService } from '../cart.service';

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
    this.cartService.addToCart(product);
    window.alert('Your product has been added to the cart!');
  }
  onNotify() {
    window.alert('You will be notified when the product goes on sale');
  }  
}


/*
Copyright Google LLC. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/