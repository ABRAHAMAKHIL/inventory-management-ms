package com.Mangrove.order_service.outbound;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
    value = "inventory",
    url = "${inventory.api-gateway.url}/inventory-service",
    configuration = FeignClientProperties.FeignClientConfiguration.class)
@CircuitBreaker(name = "inventory", fallbackMethod = "fallback")
@Retry(name = "inventory")
public interface InventoryService {

  @GetMapping("/")
  boolean checkStockAvailability(@RequestParam String skuCode, @RequestParam Integer quantity);

  default boolean fallback(String code, Integer quantity, Throwable throwable) {

    return false;
  }
}
