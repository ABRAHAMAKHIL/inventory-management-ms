package com.Mangrove.order_service.request;

import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record OrderRequest(Long id, String skuCode, BigDecimal price, Integer quantity) {}
