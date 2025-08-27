package com.Mangrove.order_service.outbound;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "inventory", url = "${inventory.api-gateway.url}/inventory-service")
public interface InventoryService {

  @GetMapping("/")
  boolean checkStockAvailability(@RequestParam String skuCode, @RequestParam Integer quantity);
}
