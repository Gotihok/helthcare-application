package com.wsei.healthcare.backend.shared.error;

import com.wsei.healthcare.backend.shared.exception.ApplicationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

public abstract class BaseExceptionHandler {

    protected ResponseEntity<AppErrorResponse> buildResponse(
            ApplicationException exception,
            HttpServletRequest request
    ) {
        return buildResponse(
                exception.getHttpStatus(),
                exception.getErrorCode(),
                exception.getMessage(),
                request
        );
    }

    protected ResponseEntity<AppErrorResponse> buildResponse(
            HttpStatus status,
            String errorCode,
            String message,
            HttpServletRequest request
    ) {
        AppErrorResponse response = new AppErrorResponse(
                Instant.now(),
                status.value(),
                errorCode,
                message,
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(response);
    }
}
