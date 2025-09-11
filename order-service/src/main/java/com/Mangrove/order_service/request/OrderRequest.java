package com.Mangrove.order_service.request;

import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record OrderRequest(
    Long id, String skuCode, BigDecimal price, Integer quantity, UserDetails userDetails) {
  public record UserDetails(String email, String firstName, String lastName) {}
}
