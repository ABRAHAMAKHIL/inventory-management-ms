package com.Mangrove.inventory_service.repository;

import com.Mangrove.inventory_service.entity.Inventory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

  boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, Integer quantity);

  @Query("select product from Inventory product where product.skuCode= :skuCode")
  Inventory getInventoryBySkuCode(String skuCode);

  @Modifying
  @Query(
      "update Inventory product set product.quantity = product.quantity - :quantity where product.skuCode = :skuCode ")
  @Transactional
  void updateProductQuantityBySkuCode(Integer quantity, String skuCode);
}
