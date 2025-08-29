package com.Mangrove.product_service.controller;

import com.Mangrove.product_service.document.Product;
import com.Mangrove.product_service.request.ProductRequest;
import com.Mangrove.product_service.response.ProductResponse;
import com.Mangrove.product_service.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product-service/")
@RequiredArgsConstructor
public class ProductController {
  @Autowired private final ProductService productService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Product> createProduct(@RequestBody ProductRequest product) {

    Product createdProduct = productService.createProduct(product);
    return ResponseEntity.ok(createdProduct);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ProductResponse> fetchProductResponse() {

    return productService.fetchAllResponse();
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<String> deleteAllProducts() {

    productService.deleteAllProducts();
    return ResponseEntity.ok("Delete All Product");
  }
}
