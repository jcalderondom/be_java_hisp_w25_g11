package com.example.be_java_hisp_w25_g11.exception;

import com.example.be_java_hisp_w25_g11.dto.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ActionNotAllowedException.class)
    public ResponseEntity<?> notFound(ActionNotAllowedException e){
        ExceptionDTO exceptionDTO = new ExceptionDTO(e.getMessage());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<?> notFound(NotFoundException e){
        ExceptionDTO exceptionDTO = new ExceptionDTO(e.getMessage());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }

}
