package com.Mangrove.order_service.config;

import com.Mangrove.order_service.outbound.InventoryServiceForStockUpdate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {

  @Bean
  public InventoryServiceForStockUpdate inventoryService() {
    RestClient restClient =
        RestClient.builder()
            .baseUrl("http://localhost:8000")
            .defaultHeader("Content-Type", "application/json")
            .build();

    HttpServiceProxyFactory factory =
        HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();

    return factory.createClient(InventoryServiceForStockUpdate.class);
  }
}
