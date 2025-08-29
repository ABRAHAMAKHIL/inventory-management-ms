package com.Mangrove.inventory_service.exception;

import org.springframework.stereotype.Component;

@Component
public class RequestExceptions extends RuntimeException {

  RequestExceptions() {

    super("Required Fields Missing");
  }
}
