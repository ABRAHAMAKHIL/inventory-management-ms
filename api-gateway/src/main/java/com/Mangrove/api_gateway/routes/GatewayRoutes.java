package com.Mangrove.api_gateway.routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.servlet.function.*;

@Configuration
public class GatewayRoutes {

  @Bean
  public RouterFunction<ServerResponse> productService() {

    return GatewayRouterFunctions.route("product_service")
        .route(
            RequestPredicates.path("/product-service/**"),
            HandlerFunctions.http("http://localhost:8060"))
        .build();
  }

  @Bean
  public RouterFunction<ServerResponse> inventoryService() {

    return GatewayRouterFunctions.route("inventory_service")
        .route(
            RequestPredicates.path("/inventory-service/**"),
            HandlerFunctions.http("http://localhost:8062"))
        .build();
  }

  @Bean
  public RouterFunction<ServerResponse> orderService() {

    return GatewayRouterFunctions.route("order_service")
        .route(
            RequestPredicates.path("/order-service/**"),
            HandlerFunctions.http("http://localhost:8061"))
        .build();
  }
}
