import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Cart } from './cart';
import { Product } from './product';
import { CartItem } from './cartItem';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) { 

    let storedCartId = this.getStoredCartId();
    if (storedCartId == null) {
      this.createCart().subscribe(resp => {
        console.log(resp);
        const keys = resp.headers.keys();
        console.log(keys);
    
        let cart = resp.body;
        console.log(cart);
        localStorage.setItem('cartId', ""+cart.id);
      });
    }
  }

  getStoredCartId() {
    let storedCartId = localStorage.getItem('cartId');
    console.log('storedCartId: '+storedCartId);
    return storedCartId;
  }

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

  removeCartItem(cartItem: CartItem): Observable<HttpResponse<Cart>> {
    console.log('CartService.removeCartItem will trigger Backend');
    return this.http.delete<Cart>(
      'http://localhost:8200/shoppingcart-api/carts/'+cartItem.cartId+'/items/'+cartItem.id,
      { observe: 'response' });
  }

  submitCart(cart: Cart): Observable<HttpResponse<Cart>> {
    console.log('CartService.submitCart will trigger Backend');
    return this.http.post<Cart>(
      'http://localhost:8200/shoppingcart-api/carts/'+cart.id+'/submit', null,
      { observe: 'response' });
  }

  updateCartItem(cartItem: CartItem): Observable<HttpResponse<Cart>> {
    console.log('CartService.updateCartItem will trigger Backend');
    return this.http.put<Cart>(
      'http://localhost:8200/shoppingcart-api/carts/'+cartItem.cartId+'/items/'+cartItem.id, cartItem,
      { observe: 'response' });
  }
  
  createCartItem(cartItem: CartItem): Observable<HttpResponse<Cart>> {
    console.log('CartService.createCartItem will trigger Backend');
    return this.http.post<Cart>(
      'http://localhost:8200/shoppingcart-api/carts/'+cartItem.cartId+'/items', cartItem,
      { observe: 'response' });
  }

  removeFromCart(cartItem: CartItem) {

    console.log('CartService.removeFromCart will call removeCartItem');
    this.removeCartItem(cartItem).subscribe(resp => {
      console.log(resp);
      const keys = resp.headers.keys();
      console.log(keys);
      console.log(resp.body);
    });
    console.log('CartService.removeCartItem finished');
  }
  
  updateQuantity(cartItem: CartItem) {

    console.log('CartService.updateQuantity will call updateCartItem');
    this.updateCartItem(cartItem).subscribe(resp => {
      console.log(resp);
      const keys = resp.headers.keys();
      console.log(keys);
      console.log(resp.body);
    });

    console.log('CartService.updateQuantity finished');
  }
  
  clearCart() {

    console.log('clearCart');
    this.createCart().subscribe(resp => {
      console.log(resp.body);
      localStorage.setItem('cartId', ""+resp.body.id);
      console.log('clearCart createCart finished');
    });
    console.log('clearCart finished');
  }

  getShippingPrices() {
    return this.http.get('/assets/shipping.json');
  }
}