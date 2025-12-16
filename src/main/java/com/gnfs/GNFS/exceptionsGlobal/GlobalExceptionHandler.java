package com.gnfs.GNFS.exceptionsGlobal;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gnfs.GNFS.exceptions.base.ApplictionException;
import com.gnfs.GNFS.exceptions.base.ResourceNotFoundException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//NB check GlobalExceptionHandler2.java for the previous code

@ControllerAdvice
public class GlobalExceptionHandler  {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /* ---------- 404: Entity not found ---------- */

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(
                        HttpStatus.NOT_FOUND,
                        ex.getMessage(),
                        request.getRequestURI()));
    }

    /* ---------- 400: Application / business errors ---------- */

    @ExceptionHandler(ApplictionException.class)
    public ResponseEntity<ErrorResponse> handleApplicationError(
            ApplictionException ex,
            HttpServletRequest request) {

        return ResponseEntity.badRequest()
                .body(ErrorResponse.of(
                        HttpStatus.BAD_REQUEST,
                        ex.getMessage(),
                        request.getRequestURI()));
    }


    /* ---------- 400: Validation errors ---------- */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        Map<String, List<String>> fieldErrors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(e ->
                    fieldErrors
                        .computeIfAbsent(e.getField(), k -> new ArrayList<>())
                        .add(e.getDefaultMessage())
                );

        return ResponseEntity.badRequest()
                .body(ErrorResponse.validation(
                        request.getRequestURI(),
                        fieldErrors));
    }

    
    /* ---------- 500: Unknown errors ---------- */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(
            Exception ex,
            HttpServletRequest request) {

        log.error("Unhandled exception", ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "An unexpected error occurred",
                        request.getRequestURI()));
    }
}
