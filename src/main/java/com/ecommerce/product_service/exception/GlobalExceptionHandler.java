package com.ecommerce.product_service.exception;

import com.ecommerce.product_service.dto.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<APIResponse<Object>> handleProductNotFound(ProductNotFoundException ex){

        APIResponse<Object>response=new APIResponse<>("ERROR", ex.getMessage(),null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<Object>> handleGeneric(Exception ex){
        APIResponse<Object>response = new APIResponse<>("ERROR","Something went wrong",null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<Object>> handleValidaton(MethodArgumentNotValidException ex){
        List<String> errors = new ArrayList<>();

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        for(FieldError error:fieldErrors){
            errors.add(error.getDefaultMessage());
        }

        APIResponse<Object> response = new APIResponse<>("ERROR","Validation failed",errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
