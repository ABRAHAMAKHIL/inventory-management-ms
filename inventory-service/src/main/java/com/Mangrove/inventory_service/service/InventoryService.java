package com.Mangrove.inventory_service.service;

import com.Mangrove.inventory_service.exception.StockExceptions;
import com.Mangrove.inventory_service.repository.InventoryRepository;
import com.Mangrove.inventory_service.request.InventoryRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

  private final InventoryRepository inventoryRepository;

  public boolean inStock(String skuCode, Integer quantity) {
    return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
  }

  @Transactional
  public void inventoryProductUpdate(InventoryRequest inventoryRequest) {
    String skuCode = inventoryRequest.skuCode();
    Integer quantity = inventoryRequest.quantity();
    if (skuCode == null && quantity == null) {
      throw new RuntimeException("Required Field Missing");
    }
    if (inStock(skuCode, quantity)) {

      inventoryRepository.updateProductQuantityBySkuCode(quantity, skuCode);
    } else {

      throw new StockExceptions(skuCode, quantity);
    }
  }
}
