package com.vasconcelos.shoppingcart.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Rodolfo Vasconcelos.
 */
@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        ApiError apiError = ApiError.builder().status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(ex.getLocalizedMessage()).build();
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}