import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Product } from '../model/product.model';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {

  productId! : string;
  product! : Product;
  productFormGroup! : FormGroup

  constructor (
    private route : ActivatedRoute,
    public productService : ProductService,
    private fb : FormBuilder
  ) {
    this.productId = this.route.snapshot.params['id'];
  }

  ngOnInit(): void {

    this.productService.getProduct(this.productId).subscribe({

      next : (product) => {
        this.product = product;

        this.productFormGroup = this.fb.group({
          name : this.fb.control(this.product.name, [Validators.required, Validators.minLength(4)]),
          price : this.fb.control(this.product.price, [Validators.required, Validators.min(200)]),
          promotion : this.fb.control(this.product.promotion, [Validators.required]),
        })
      },
      error : (err) => {
        console.log(err);
      }

    });
      
  }

  handleUpdateProduct ()
  {

    let product = this.productFormGroup.value;
    product.id = this.product.id;

    this.productService.updateProduct(product).subscribe({
      next : (prod) => {
        alert("Product updated successfully")
      },
      error : err => {
        console.log(err);
      }
    });

  }

}
