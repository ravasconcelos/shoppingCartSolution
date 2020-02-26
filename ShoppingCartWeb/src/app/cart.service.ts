import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Cart } from './cart';
import { CartItem } from './cartItem';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) { 
    console.log('CartService.constructor');
    this.createCart().subscribe(resp => {
      console.log(resp);
      const keys = resp.headers.keys();
      console.log(keys);
  
      this.cart = resp.body;
      console.log(this.cart);
    });
    console.log('CartService.constructor finished');
  }

  cart: Cart;
  items = [];
  productMap = new Map();

  createCart(): Observable<HttpResponse<Cart>> {
    console.log('CartService.createCart will trigger Backend');
    return this.http.post<Cart>(
      'http://localhost:8200/shoppingcart-api/carts', null,
      { observe: 'response' });
  }

  getCart(id: number): Observable<HttpResponse<Cart>> {
    console.log('CartService.getCart will trigger Backend');
    return this.http.get<Cart>(
      'http://localhost:8200/shoppingcart-api/carts/'+id,
      { observe: 'response' });
  }

  createCartItem(cartItem: CartItem): Observable<HttpResponse<Cart>> {
    console.log('CartService.createCartItem will trigger Backend');
    return this.http.post<Cart>(
      'http://localhost:8200/shoppingcart-api/carts/'+cartItem.cartId+'/items', cartItem,
      { observe: 'response' });
  }

  addToCart(product) {
    this.items.push(product);

    var cartItem: CartItem = new CartItem();
    cartItem.cartId = this.cart.id;
    cartItem.productId = product.id;
    cartItem.price = product.price;
    cartItem.quantity = 1;
    this.cart.items.push(cartItem);

    console.log('CartService.addToCart will call createCartItem');
    this.createCartItem(cartItem).subscribe(resp => {
      console.log(resp);
      const keys = resp.headers.keys();
      console.log(keys);
      this.cart = resp.body;
      console.log(this.cart);
    });
    console.log('CartService.addToCart finished');

    this.productMap.set(product.id, product);
  }
  
  getItems() {
    return this.items;
  }

  getProductMap() {
    return this.productMap;
  }
  
  getShoppingCart() {
    console.log('CartService.getShoppingCart');
    this.getCart(this.cart.id).subscribe(resp => {
      console.log(resp);
      const keys = resp.headers.keys();
      console.log(keys);
      this.cart = resp.body;
      console.log(this.cart);
    });
    console.log('CartService.getShoppingCart finished');
    return this.cart;
  }

  clearCart() {
    this.items = [];
    return this.items;
  }

  getShippingPrices() {
    return this.http.get('/assets/shipping.json');
  }
}