package com.crud.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<Object> handleClientNotFoundException(ClientNotFoundException exception){
        return new ResponseEntity<>("Client with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DriverNotFoundException.class)
    public ResponseEntity<Object> handleDriverNotFoundException(DriverNotFoundException exception){
        return new ResponseEntity<>("Driver with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException exception){
        return new ResponseEntity<>("Order with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<Object> handleVehicleNotFoundException(VehicleNotFoundException exception){
        return new ResponseEntity<>("Vehicle with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }
}
