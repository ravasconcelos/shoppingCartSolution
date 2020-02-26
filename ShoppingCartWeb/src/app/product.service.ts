import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Product } from './product';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  getProducts(): Observable<HttpResponse<Product[]>> {
    console.log('ProductService.getProducts will trigger Backend');
    return this.http.get<Product[]>(
      'http://localhost:8200/shoppingcart-api/products', 
      { observe: 'response' });
  }

  getProduct(productId): Observable<HttpResponse<Product>> {
    console.log('ProductService.getProduct will trigger Backend. productId'+productId);
    return this.http.get<Product>(
      'http://localhost:8200/shoppingcart-api/products/'+productId, 
      { observe: 'response' });
  }
    

}