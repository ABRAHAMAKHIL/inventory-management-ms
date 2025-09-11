package com.Mangrove.order_service.service;

import com.Mangrove.order_service.entity.Order;
import com.Mangrove.order_service.event.OrderPlacedEvent;
import com.Mangrove.order_service.exception.InventoryExceptions;
import com.Mangrove.order_service.outbound.InventoryService;
import com.Mangrove.order_service.outbound.InventoryServiceForStockUpdate;
import com.Mangrove.order_service.repository.OrderRepository;
import com.Mangrove.order_service.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

  private final OrderRepository orderRepository;
  private final InventoryService inventoryService;
  private final InventoryServiceForStockUpdate inventoryServiceForStockUpdate;
  private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

  public void placeOrder(OrderRequest orderRequest) {
    var isProductInStock =
        inventoryService.checkStockAvailability(orderRequest.skuCode(), orderRequest.quantity());

    if (isProductInStock) {
      Order order =
          Order.builder()
              .number(UUID.randomUUID().toString())
              .quantity(orderRequest.quantity())
              .skuCode(orderRequest.skuCode())
              .price(orderRequest.price())
              .build();

      orderRepository.save(order);

      OrderPlacedEvent orderPlacedEvent =
          new OrderPlacedEvent(order.getNumber(), orderRequest.userDetails().email());
      log.info(
          "Start sending orderPlacedEvent {} event to Kafka topic order-place", orderPlacedEvent);
      kafkaTemplate.send("order-placed", orderPlacedEvent);

      log.info(
          "End sending orderPlacedEvent {} event to Kafka topic order-place", orderPlacedEvent);

      updateInventory(order.getSkuCode(), order.getQuantity());
    } else {

      throw new InventoryExceptions(orderRequest.skuCode(), orderRequest.quantity());
    }
  }

  private void updateInventory(String skuCode, Integer quantity) {
    try {
      String result =
          inventoryServiceForStockUpdate.updateStock(
              new InventoryServiceForStockUpdate.InventoryRequest(skuCode, quantity));
      log.info("Inventory update response: {}", result);
    } catch (Exception e) {
      log.error("Failed to update inventory for skuCode: {}. Error: {}", skuCode, e.getMessage());
      throw new RuntimeException("Failed to update inventory: " + e.getMessage());
    }
  }
}
