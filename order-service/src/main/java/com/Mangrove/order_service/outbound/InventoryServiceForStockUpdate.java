package com.Mangrove.order_service.outbound;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

public interface InventoryServiceForStockUpdate {

  @PostExchange("/inventory-service/")
  String updateStock(@RequestBody InventoryRequest request);

  record InventoryRequest(String skuCode, Integer quantity) {}
}
