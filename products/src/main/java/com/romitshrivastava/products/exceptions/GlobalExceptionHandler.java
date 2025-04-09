package com.romitshrivastava.products.exceptions;

import com.romitshrivastava.products.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleProductAlreadyExistsException
            (ProductAlreadyExistsException ex , WebRequest request) {

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(request.getDescription(false),ex.getMessage(), HttpStatus.CONFLICT, LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponseDTO);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException
            (ResourceNotFoundException ex , WebRequest request) {

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO
                (request.getDescription(false),ex.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException
            (MethodArgumentNotValidException exception, WebRequest webRequest){
        List<ObjectError> validationErrorList = exception.getBindingResult().getAllErrors();
        HashMap<String,String> validationErrors = new HashMap<>();

        validationErrorList.forEach((error) ->{
            String fieldName = ((FieldError) error).getField();
            String msg = error.getDefaultMessage();
            validationErrors.put(fieldName,msg);
        });


        return new ResponseEntity<>(validationErrors,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGlobalException
            (Exception exception, WebRequest webRequest){

        ErrorResponseDTO e = new ErrorResponseDTO(webRequest.getDescription(false),
                exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());

        return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
