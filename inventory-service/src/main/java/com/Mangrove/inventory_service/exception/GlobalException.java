package com.Mangrove.inventory_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

  @ExceptionHandler(StockExceptions.class)
  public ResponseEntity<String> handleStockException(StockExceptions exception) {

    return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(RequestExceptions.class)
  public ResponseEntity<String> handleStockException(RequestExceptions exception) {

    return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
