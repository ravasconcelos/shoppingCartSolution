import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { ProductService } from '../product.service';
import { CartService } from '../cart.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  product;
  quantityForm

  constructor(
    private route: ActivatedRoute,
    private cartService: CartService,
    private productService: ProductService,
    private formBuilder: FormBuilder,
  ) { 
    this.quantityForm = this.formBuilder.group({
      quantity: '1'
    });
  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.productService.getProduct(params.get('productId')).subscribe(resp => {
        console.log(resp);
        const keys = resp.headers.keys();
        console.log(keys);
        this.product = resp.body;
      });
    });
  }

  addToCart(product) {
    this.cartService.addToCart(product);
    window.alert('Your product has been added to the cart!');
  }

  onSubmit(formData, product) {
    console.log("formData.quantity: "+formData.quantity);
    product.quantity = formData.quantity;
    console.log("product.quantity: "+product.quantity);
    this.cartService.addToCart(product);
    window.alert('Your product has been added to the cart!');
  }

}