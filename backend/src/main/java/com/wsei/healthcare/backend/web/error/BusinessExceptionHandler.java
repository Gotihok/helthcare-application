package com.wsei.healthcare.backend.web.error;

import com.wsei.healthcare.backend.api.error.AppErrorResponse;
import com.wsei.healthcare.backend.infra.common.exception.AlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BusinessExceptionHandler extends BaseExceptionHandler {

    // TODO: add validation error interception

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<AppErrorResponse> handleAlreadyExists(
            AlreadyExistsException ex,
            HttpServletRequest request
    ) {
        return buildResponse(
                ex.getHttpStatus(),
                ex.getErrorCode(),
                ex.getMessage(),
                request
        );
    }
}
