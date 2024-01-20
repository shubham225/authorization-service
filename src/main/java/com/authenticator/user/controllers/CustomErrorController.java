package com.authenticator.user.controllers;

import com.authenticator.user.dtos.ExceptionDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping(
            path = "/error"
    )
    public ResponseEntity<ExceptionDto> handleError(Exception exception) {
        return new ResponseEntity<>(new ExceptionDto(new Date(), HttpStatus.BAD_REQUEST, exception.getMessage()),
                                    HttpStatus.BAD_REQUEST);
    }

//    @RequestMapping(
//            path = "/error"
//    )
//    public ResponseEntity<ExceptionDto> handleError(HttpServletRequest request) {
//        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
//        return new ResponseEntity<>(new ExceptionDto(new Date(), statusCode, HttpStatus.valueOf(statusCode), exception.getMessage()),
//                HttpStatus.valueOf(statusCode));
//    }
}
