package com.authenticator.user.controllers;

import com.authenticator.user.dtos.ExceptionDto;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping(
            path = "/error"
    )
    public ResponseEntity<ExceptionDto> handleError(Exception exception) {
        return new ResponseEntity<>(new ExceptionDto(HttpStatus.BAD_REQUEST, exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
