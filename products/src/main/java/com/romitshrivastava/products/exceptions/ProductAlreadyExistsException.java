package com.romitshrivastava.products.exceptions;


import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.CONFLICT)
public class ProductAlreadyExistsException  extends RuntimeException{
    public ProductAlreadyExistsException(String message) {
        super(message);
    }
}
