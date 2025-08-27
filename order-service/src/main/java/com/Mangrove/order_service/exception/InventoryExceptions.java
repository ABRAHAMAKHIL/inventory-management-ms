package com.Mangrove.order_service.exception;

import org.springframework.stereotype.Component;

@Component
public class InventoryExceptions extends RuntimeException{

    InventoryExceptions(){

        super("not in stock");
    }

  public InventoryExceptions(String skuCode, Integer quantity) {
        super(String.format("Product with skuCode %s is not in stock. Requested quantity: %d", skuCode, quantity));
    }
}
