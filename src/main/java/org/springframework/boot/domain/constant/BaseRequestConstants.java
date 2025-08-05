package org.springframework.boot.domain.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BaseRequestConstants {

    /// /////////************* REQUESTBODY *************////////////
    /// /////////************* IOS *************////////////
    public static final String IOS_DEVICE = """
            {
    
                "osVersion": "17.4.1",
                "model": "iPhone 11",
                "longitude": "29.14213777279719",
                "latitude": "40.99874869265077",
                "brand": "Apple",
                "network": "WIFI"
            }
            """;
    String IOSHEADERV1 = """
            {
              "channel": "APP_IOS",
              "tranId": "1E596F54-5AB1-4B40-9E6E-0EBE6B7E8393",
              "tranDate": "2024-05-27T07:30:49.568Z"
            }
            """;

    String currentTimestamp = java.time.OffsetDateTime.now().toString();
    public static final String IOS_HEADER = String.format(IOSHEADERV1, currentTimestamp);

    /// /////////************* ANDROID *************////////////
    public static final String ANDROID_DEVICE = """
            {
              
                "id": "0f9521a3-2d4b-4e13-b5fa-015efda1799c",
                "model": "sdk_gphone64_arm64",
                "network": "WIFI",
                "os": "ANDROID",
                "osVersion": "34"
            }
            """;
    public static final String ANDROID_HEADER = """
            {
             "channel": "APP_ANDROID",
             "tranDate": "2025-01-02T07:33:39.920Z",
             "tranId": "ee46b55c-0c15-45f5-b800-9591134beb2d"
            }
            """;

}
