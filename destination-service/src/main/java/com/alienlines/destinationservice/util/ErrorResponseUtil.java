package com.alienlines.destinationservice.util;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorResponseUtil {

    public static ResponseEntity errorResponse(Exception ex) {

        ErrorResponse response = new ErrorResponse();
        response.setErrorCode(400);
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }
}
