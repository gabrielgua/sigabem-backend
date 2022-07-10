package com.projetoapi.controller;

import com.projetoapi.exception.StandardError;
import com.projetoapi.exception.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validationErrors(MethodArgumentNotValidException ex, HttpServletRequest request) {

        ValidationError errors = new ValidationError(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation Error",
                "Erro na validação dos campos", request.getRequestURI());


        for (FieldError f : ex.getBindingResult().getFieldErrors()) {
            errors.addError(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> httpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletRequest request) {
        StandardError error = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation Error",
                "Peso inválido!",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<StandardError> httpClientErrorException(HttpClientErrorException ex, HttpServletRequest request) {
        StandardError error = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation Error",
                "CEP inválido!",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
