package com.userservice.authorization.controllers;

import com.userservice.authorization.dtos.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping(
            path = "/error"
    )
    public ResponseEntity<ErrorResponseDto> handleError(Exception exception, HttpServletRequest webRequest) {
        ErrorResponseDto error = new ErrorResponseDto(exception, webRequest);
        HttpStatus status = HttpStatus.BAD_REQUEST;

        error.setStatus(status.toString());

        return new ResponseEntity<ErrorResponseDto>(error, status);
    }
}
