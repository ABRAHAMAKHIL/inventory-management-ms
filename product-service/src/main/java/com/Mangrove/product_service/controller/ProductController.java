package com.Mangrove.product_service.controller;

import com.Mangrove.product_service.request.ProductRequest;
import com.Mangrove.product_service.response.ProductResponse;
import com.Mangrove.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-service/")
@RequiredArgsConstructor
public class ProductController {
  @Autowired private final ProductService productService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createProduct(@RequestBody ProductRequest product) {

    productService.createProduct(product);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ProductResponse> fetchProductResponse() {

    return productService.fetchAllResponse();
  }
}
