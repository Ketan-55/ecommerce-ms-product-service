package com.ecommerce.product_service.exception;

import com.ecommerce.product_service.dto.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<APIResponse<Object>> handleProductNotFound(ProductNotFoundException ex){

        APIResponse<Object>response=new APIResponse<>("ERROR", ex.getMessage(),null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }


    public ResponseEntity<APIResponse<Object>> handleGeneric(Exception ex){
        APIResponse<Object>response = new APIResponse<>("ERROR","Something went wrong",null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
