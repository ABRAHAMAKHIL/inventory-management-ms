package com.Mangrove.inventory_service.exception;

import org.springframework.stereotype.Component;

@Component
public class StockExceptions extends RuntimeException {

  public StockExceptions() {
    super("Stock not available");
  }

  public StockExceptions(String skuCode, Integer quantity) {
    super(
        String.format(
            "Product with skuCode %s is not in stock. Requested quantity: %d", skuCode, quantity));
  }
}
