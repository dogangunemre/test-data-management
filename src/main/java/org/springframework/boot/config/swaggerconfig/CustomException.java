package org.springframework.boot.config.swaggerconfig;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
public class CustomException extends RuntimeException {
    private final Map<String, Object> errorDetails;
    private final HttpStatus status;

    public CustomException(Map<String, Object> errorDetails, HttpStatus status) {
        super(errorDetails.toString());
        this.errorDetails = errorDetails;
        this.status = status;
    }
}
