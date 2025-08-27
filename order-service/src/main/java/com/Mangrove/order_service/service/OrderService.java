package com.Mangrove.order_service.service;

import com.Mangrove.order_service.entity.Order;
import com.Mangrove.order_service.repository.OrderRepository;
import com.Mangrove.order_service.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

  private final OrderRepository orderRepository;

  public void placeOrder(OrderRequest orderRequest) {

    Order order =
        Order.builder()
            .number(UUID.randomUUID().toString())
            .quantity(orderRequest.quantity())
            .skuCode(orderRequest.skuCode())
            .price(orderRequest.price())
            .build();

    orderRepository.save(order);

    log.info("order {} saved " + order.getId());
  }
}
