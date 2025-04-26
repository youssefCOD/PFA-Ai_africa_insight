package com.africainsight.newsservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionManager {
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationException(
      MethodArgumentNotValidException exception) {
    Map<String, String> error = new HashMap<>();
    exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
      error.put(fieldError.getField(), fieldError.getDefaultMessage());
    });
    return ResponseEntity.badRequest().body(error);
  }
}
