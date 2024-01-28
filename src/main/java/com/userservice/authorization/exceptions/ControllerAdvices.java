package com.userservice.authorization.exceptions;

import com.userservice.authorization.dtos.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvices {
    @ExceptionHandler(NullUserRolesException.class)
    public ResponseEntity<ErrorResponseDto> handleNullUserRolesException(NullUserRolesException exception, HttpServletRequest request) {
        ErrorResponseDto error = new ErrorResponseDto(exception, request);
        HttpStatus status = HttpStatus.BAD_REQUEST;

        error.setStatus(status.toString());

        return new ResponseEntity<ErrorResponseDto>(error, status);
    }

    @ExceptionHandler(  {
                        AccessDeniedException.class,
                        InvalidBearerTokenException.class,
                        InsufficientAuthenticationException.class,
                        InvalidBearerTokenException.class
                        } )
    public ResponseEntity<ErrorResponseDto> handleAccessDeniedException(Exception exception, HttpServletRequest request) {
        ErrorResponseDto error = new ErrorResponseDto(exception, request);
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        error.setStatus(status.toString());

        return new ResponseEntity<ErrorResponseDto>(error, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception exception, HttpServletRequest request) {
        ErrorResponseDto error = new ErrorResponseDto(exception, request);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        error.setStatus(status.toString());

        return new ResponseEntity<ErrorResponseDto>(error, status);
    }
}
