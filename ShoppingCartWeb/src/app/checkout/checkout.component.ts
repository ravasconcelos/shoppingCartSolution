import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { FormBuilder } from '@angular/forms';
import { CartService } from '../cart.service';
import { Cart } from '../cart';
import { Product } from '../product';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  items;
  productMap: Map<string, Product>;
  cart: Cart;
  checkoutForm;

  constructor(
    private cartService: CartService,
    private formBuilder: FormBuilder,
    private router: Router
  ) { 
    this.checkoutForm = this.formBuilder.group({
      name: '',
      address: ''
    });
  }

  ngOnInit() {
    this.items = this.cartService.getItems();
    this.productMap = this.cartService.getProductMap();
    this.cart = this.cartService.getShoppingCart();
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
      this.cart = this.cartService.getShoppingCart();
      this.router.navigateByUrl('/order');
    });
  }
}