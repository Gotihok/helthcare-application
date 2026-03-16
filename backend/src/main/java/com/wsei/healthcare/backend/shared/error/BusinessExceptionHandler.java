package com.wsei.healthcare.backend.shared.error;

import com.wsei.healthcare.backend.shared.exception.AlreadyExistsAppException;
import com.wsei.healthcare.backend.shared.exception.ApplicationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BusinessExceptionHandler extends BaseExceptionHandler {

    // TODO: add validation error interception for proper error modeling

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<AppErrorResponse> handleApplicationBusinessException(
            AlreadyExistsAppException ex,
            HttpServletRequest request
    ) {
        return buildResponse(ex, request);
    }
}
