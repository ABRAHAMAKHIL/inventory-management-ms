package com.Mangrove.product_service.service;

import com.Mangrove.product_service.document.Product;
import com.Mangrove.product_service.repository.ProductRepository;
import com.Mangrove.product_service.request.ProductRequest;
import com.Mangrove.product_service.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

  private final ProductRepository repository;

  public void createProduct(ProductRequest apiRequest) {

    Product product =
        Product.builder()
            .name(apiRequest.getName())
            .price(apiRequest.getPrice())
            .description(apiRequest.getDescription())
            .build();

    repository.save(product);

    log.info("Product {} is saved", product.getId());
  }

  public List<ProductResponse> fetchAllResponse() {

    List<Product> product = repository.findAll();

    return product.stream()
        .map(
            items ->
                ProductResponse.builder()
                    .id(items.getId())
                    .name(items.getName())
                    .description(items.getDescription())
                    .price(items.getPrice())
                    .build())
        .toList();
  }
}
