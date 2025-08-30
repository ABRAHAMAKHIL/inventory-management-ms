package com.Mangrove.api_gateway.routes;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;

import java.net.URI;
import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.*;

@Configuration
public class GatewayRoutes {

  @Bean
  public RouterFunction<ServerResponse> productService() {

    return route("product_service")
        .route(
            RequestPredicates.path("/product-service/**"),
            HandlerFunctions.http("http://localhost:8060"))
        .filter(
            CircuitBreakerFilterFunctions.circuitBreaker(
                "productServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
        .build();
  }

  @Bean
  public RouterFunction<ServerResponse> inventoryService() {

    return route("inventory_service")
        .route(
            RequestPredicates.path("/inventory-service/**"),
            HandlerFunctions.http("http://localhost:8062"))
        .filter(
            CircuitBreakerFilterFunctions.circuitBreaker(
                "inventoryServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
        .build();
  }

  @Bean
  public RouterFunction<ServerResponse> orderService() {

    return route("order_service")
        .route(
            RequestPredicates.path("/order-service/**"),
            HandlerFunctions.http("http://localhost:8061"))
        .filter(
            CircuitBreakerFilterFunctions.circuitBreaker(
                "orderServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
        .build();
  }

  @Bean
  public RouterFunction<ServerResponse> fallbackRoute() {
    return RouterFunctions.route()
        .GET(
            "/fallbackRoute",
            request -> {
              String originalUri = request.headers().firstHeader("X-Gateway-Request-Url");
              String path = request.path();
              String message =
                  "Service Unavailable for path: " + path + ". Please retry after some time.";
              return ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE).body(message);
            })
        .build();
  }
}
