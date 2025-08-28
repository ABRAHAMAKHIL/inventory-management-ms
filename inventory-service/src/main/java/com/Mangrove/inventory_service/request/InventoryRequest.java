package com.Mangrove.inventory_service.request;

import lombok.Builder;

@Builder
public record InventoryRequest(Integer quantity, String skuCode) {}
