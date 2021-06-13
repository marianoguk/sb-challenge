package com.sb.ecommerce.infrastructure.api.endpoint;

import com.sb.ecommerce.domain.exception.DomainException;
import com.sb.ecommerce.domain.exception.ExceptionType;
import com.sb.ecommerce.infrastructure.api.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
public class ExceptionMapper {
    private static final Map<ExceptionType, Integer> errorMapping = errorMapping();

    private static Map<ExceptionType, Integer> errorMapping() {
        Map<ExceptionType, Integer> result = new HashMap();
        result.put(ExceptionType.BACK_SCRATCHER_CREATION, 400);
        result.put(ExceptionType.BACK_SCRATCHER_CREATION_DUPLICATED_NAME, 400);
        result.put(ExceptionType.BACK_SCRATCHER_EDITION, 400);
        result.put(ExceptionType.BACK_SCRATCHER_DELETION, 400);
        result.put(ExceptionType.BACK_SCRATCHER_NOT_FOUND, 404);
        return result;
    }

    public ErrorDto map(DomainException e, ServletWebRequest request){
        int statusCode = errorMapping().getOrDefault(e.getType(), 500);
        HttpServletRequest rq = request.getNativeRequest(HttpServletRequest.class);

        return new ErrorDto(
                e.getType().getEntity(),
                e.getType().getType(),
                e.getType(),
                HttpStatus.valueOf(statusCode),
                rq.getRequestURI(),
                request.getHttpMethod(),
                request.getSessionId()
        );
    }

    public ErrorDto map(Exception exception, ServletWebRequest rq) {
        return map(new DomainException(ExceptionType.BACK_SCRATCHER_UNEXPECTED), rq);
    }
}