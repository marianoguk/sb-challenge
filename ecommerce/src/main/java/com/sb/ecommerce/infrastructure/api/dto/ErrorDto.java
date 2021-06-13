package com.sb.ecommerce.infrastructure.api.dto;

import com.sb.ecommerce.domain.exception.ExceptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ErrorDto {
    private String resource;
    private String description;
    private ExceptionType code;
    private HttpStatus status;
    private String uri;
    private HttpMethod method;
    private String sessionId;

}
