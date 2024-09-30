package com.userservice.authorization.exception.handler;

import com.userservice.authorization.exception.*;
import com.userservice.authorization.model.dto.ExceptionDTO;
import com.userservice.authorization.model.result.AppResult;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class ControllerAdviceImpl {
    @ExceptionHandler(NullUserRolesException.class)
    public ResponseEntity<AppResult> handleNullUserRolesException(NullUserRolesException exception, HttpServletRequest request) {
        ExceptionDTO error = new ExceptionDTO(exception, request);
        HttpStatus status = HttpStatus.BAD_REQUEST;

        error.setStatus(status.toString());

        return AppResult.badRequest(exception.getMessage(), error);
    }

    @ExceptionHandler({
            ClientAlreadyExistsException.class,
            IllegalValueException.class,
            UserAlreadyExistsException.class,
            RoleNotFoundException.class
    })
    public ResponseEntity<AppResult> handleValidationExceptions(Exception exception, HttpServletRequest request) {
        ExceptionDTO error = new ExceptionDTO(exception, request);
        HttpStatus status = HttpStatus.BAD_REQUEST;

        error.setStatus(status.toString());

        return AppResult.badRequest(exception.getMessage(), error);
    }

    @ExceptionHandler({
            AccessDeniedException.class,
            InvalidBearerTokenException.class,
            InsufficientAuthenticationException.class,
            InvalidBearerTokenException.class
    })
    public ResponseEntity<AppResult> handleUnauthorizedAccessException(Exception exception, HttpServletRequest request) {
        ExceptionDTO error = new ExceptionDTO(exception, request);
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        error.setStatus(status.toString());

        return AppResult.unauthorized(exception.getMessage(), error);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoResourceFoundException(Exception exception, HttpServletRequest request) {
        return "forward:/";
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AppResult> handleException(Exception exception, HttpServletRequest request) {
        ExceptionDTO error = new ExceptionDTO(exception, request);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        error.setStatus(status.toString());

        return AppResult.error(exception.getMessage(), error);
    }
}
