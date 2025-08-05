package org.springframework.boot.config.swaggerconfig;

import org.springframework.boot.domain.constant.AppConstants;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class RestTemplateHelper {

    public static ResponseEntity<String> sendPostRequest(RestTemplate restTemplate, String url, HttpEntity<String> requestEntity) {
        return sendRequest(restTemplate, url, requestEntity, HttpMethod.POST);
    }

    public static ResponseEntity<String> sendGetRequest(RestTemplate restTemplate, String url, HttpEntity<String> requestEntity) {
        return sendRequest(restTemplate, url, requestEntity, HttpMethod.GET);
    }

    public static ResponseEntity<String> sendPutRequest(RestTemplate restTemplate, String url, HttpEntity<String> requestEntity) {
        return sendRequest(restTemplate, url, requestEntity, HttpMethod.PUT);
    }

    public static ResponseEntity<String> sendDeleteRequest(RestTemplate restTemplate, String url, HttpEntity<String> requestEntity) {
        return sendRequest(restTemplate, url, requestEntity, HttpMethod.DELETE);
    }

    private static ResponseEntity<String> sendRequest(RestTemplate restTemplate, String url, HttpEntity<String> requestEntity, HttpMethod method) {
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, method, requestEntity, String.class);

            System.out.println(" Başarılı Yanıt:");
            System.out.println(AppConstants.URL + url);
            System.out.println((AppConstants.REQUSET_BODY + (requestEntity != null ? requestEntity.getBody() : "YOK")));
            System.out.println(" Response Status: " + response.getStatusCode());
            System.out.println(AppConstants.RESPONSE_BODY + response.getBody());

            return response;
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            System.err.println(" HATA OLUŞTU: " + e.getStatusCode());
            System.err.println(" Hata mesajı: " + e.getResponseBodyAsString());
            System.err.println(AppConstants.URL + url);
            System.err.println(AppConstants.REQUSET_BODY + (requestEntity != null ? requestEntity.getBody() : "YOK"));

            Map<String, Object> errorDetails = new HashMap<>();
            errorDetails.put("timestamp", LocalDateTime.now());
            errorDetails.put("error", HttpStatus.valueOf(e.getStatusCode().value()).getReasonPhrase());
            errorDetails.put(" Hata mesajı", e.getResponseBodyAsString());
            errorDetails.put(AppConstants.URL, url);
            errorDetails.put(AppConstants.REQUSET_BODY, requestEntity != null ? requestEntity.getBody() : "YOK");
            errorDetails.put(AppConstants.RESPONSE_BODY, e.getResponseBodyAsString());

            System.err.println(AppConstants.RESPONSE_BODY + e.getResponseBodyAsString());

            throw new CustomException(errorDetails, HttpStatus.valueOf(e.getStatusCode().value()));
        } catch (Exception e) {
            System.err.println(" Beklenmeyen bir hata oluştu: " + e.getMessage());
            System.err.println(AppConstants.URL + url);
            System.err.println(AppConstants.REQUSET_BODY + (requestEntity != null ? requestEntity.getBody() : "YOK"));

            Map<String, Object> errorDetails = new HashMap<>();
            errorDetails.put("timestamp", LocalDateTime.now());
            errorDetails.put("error", "Internal Server Error");
            errorDetails.put("message", e.getMessage());

            throw new CustomException(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
