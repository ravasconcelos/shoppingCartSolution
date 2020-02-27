import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { FormBuilder } from '@angular/forms';
import { CartService } from '../cart.service';
import { ProductService } from '../product.service';
import { Cart } from '../cart';
import { Product } from '../product';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  productMap: Map<string, Product>;
  cart: Cart;
  checkoutForm;

  constructor(
    private cartService: CartService,
    private productService: ProductService,
    private formBuilder: FormBuilder,
    private router: Router
  ) { 
    this.checkoutForm = this.formBuilder.group({
      name: '',
      address: ''
    });
  }

  ngOnInit() {
    this.productMap = this.productService.getProductMap();
    this.cart = this.getShoppingCart();
  }

  onSubmit(customerData) {
    console.log('CartService.submitOrder will call submitCart');
    this.cartService.submitCart(this.cart).subscribe(resp => {
      console.log(resp);
      const keys = resp.headers.keys();
      console.log(keys);
      console.log(resp.body);

      console.log('CartService.submitOrder will call getShoppingCart');
      window.alert('Your order has been submitted!');
      this.cart = this.getShoppingCart();
      this.router.navigateByUrl('/order');
    });
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