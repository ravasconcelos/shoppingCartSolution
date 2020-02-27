import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Product } from './product';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  productMap = new Map();

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
    
  getProductMap() {
    console.log("getProductMap");
    if (this.productMap.size==0) {
      this.getProducts().subscribe(resp => {
        console.log(resp);
        const keys = resp.headers.keys();
        console.log(keys);
    
        for (const data of resp.body) {
          console.log(data);
          this.productMap.set(data.id, data);
        }
      });
    } else {
      console.log("this.productMap is already populated");
    }
    return this.productMap;
  }
}