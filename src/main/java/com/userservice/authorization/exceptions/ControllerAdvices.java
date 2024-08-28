package com.userservice.authorization.exceptions;

import com.userservice.authorization.dtos.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class ControllerAdvices {
    @ExceptionHandler(NullUserRolesException.class)
    public ResponseEntity<ErrorResponseDto> handleNullUserRolesException(NullUserRolesException exception, HttpServletRequest request) {
        ErrorResponseDto error = new ErrorResponseDto(exception, request);
        HttpStatus status = HttpStatus.BAD_REQUEST;

        error.setStatus(status.toString());

        return new ResponseEntity<ErrorResponseDto>(error, status);
    }

    @ExceptionHandler({
            ClientAlreadyExistsException.class,
            IllegalValueException.class,
            UserAlreadyExistsException.class
    })
    public ResponseEntity<ErrorResponseDto> handleValidationExceptions(Exception exception, HttpServletRequest request) {
        ErrorResponseDto error = new ErrorResponseDto(exception, request);
        HttpStatus status = HttpStatus.BAD_REQUEST;

        error.setStatus(status.toString());

        return new ResponseEntity<ErrorResponseDto>(error, status);
    }

    @ExceptionHandler({
            AccessDeniedException.class,
            InvalidBearerTokenException.class,
            InsufficientAuthenticationException.class,
            InvalidBearerTokenException.class
    })
    public ResponseEntity<ErrorResponseDto> handleUnauthorizedAccessException(Exception exception, HttpServletRequest request) {
        ErrorResponseDto error = new ErrorResponseDto(exception, request);
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        error.setStatus(status.toString());

        return new ResponseEntity<ErrorResponseDto>(error, status);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoResourceFoundException(Exception exception, HttpServletRequest request) {
        return "forward:/";
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception exception, HttpServletRequest request) {
        ErrorResponseDto error = new ErrorResponseDto(exception, request);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        error.setStatus(status.toString());

        return new ResponseEntity<ErrorResponseDto>(error, status);
    }
}
