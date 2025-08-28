package com.Mangrove.inventory_service.controller;

import com.Mangrove.inventory_service.request.InventoryRequest;
import com.Mangrove.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory-service/")
@RequiredArgsConstructor
public class InventoryController {

  public final InventoryService inventoryService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public boolean inStock(@RequestParam String skuCode, @RequestParam Integer quantity) {
    return inventoryService.inStock(skuCode, quantity);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<String> stockUpdate(@RequestBody InventoryRequest apiRequest) {
    inventoryService.inventoryProductUpdate(apiRequest);

    return ResponseEntity.ok("Product quantity updated");
  }
}
