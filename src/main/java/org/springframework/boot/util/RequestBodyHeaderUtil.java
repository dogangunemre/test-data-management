package org.springframework.boot.util;

import org.springframework.boot.domain.constant.AppConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class RequestBodyHeaderUtil {
    public static final String BEARER = "Bearer ";

    public HttpHeaders getBasicHeader(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (token != null && !token.isEmpty()) {
            headers.set(AppConstants.AUTHORIZATION, BEARER + token);
        }
        return headers;
    }

    public HttpHeaders getOceanHeader(String oceanToken, String oceanCorrelationId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public HttpHeaders getSealHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
