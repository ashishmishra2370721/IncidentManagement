package com.cts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cts.payload.ApiResponse;



@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler  {
	
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handlerException(BusinessException ex){
        String message = ex.getMessage();
        ApiResponse res =ApiResponse.builder().message(message).success(false).httpStatus(HttpStatus.INTERNAL_SERVER_ERROR).build();
        return new ResponseEntity<>(res,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse> handlerBusinessException(BusinessException ex){
        String message = ex.getMessage();
        ApiResponse res =ApiResponse.builder().message(message).success(false).httpStatus(HttpStatus.FORBIDDEN).build();
        return new ResponseEntity<>(res,HttpStatus.FORBIDDEN);
    }
}
