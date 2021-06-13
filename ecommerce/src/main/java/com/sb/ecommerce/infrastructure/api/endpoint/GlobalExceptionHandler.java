package com.sb.ecommerce.infrastructure.api.endpoint;

import com.sb.ecommerce.domain.exception.DomainException;
import com.sb.ecommerce.domain.exception.ExceptionType;
import com.sb.ecommerce.infrastructure.api.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.connector.ResponseFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private ExceptionMapper errorMapper;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleAllUncaughtException(Exception exception, WebRequest request) {
        log.error("Handling exception {}", exception.getMessage(), exception);
        return buildResponse(errorMapper.map(exception, (ServletWebRequest)request));
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Object> handleDomainException(DomainException exception, WebRequest request) {
        log.error("Handling Domain exception {}", exception.getMessage(), exception);
        ErrorDto error = errorMapper.map(exception, (ServletWebRequest)request);
        return buildResponse(error);
    }

    private ResponseEntity<Object> buildResponse(ErrorDto error) {
        return ResponseEntity.status(error.getStatus()).body(error);
    }

}
