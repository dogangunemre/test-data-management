package org.springframework.boot.domain.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AppConstants {

    /// /////////************* PATH *************////////////
    public static final String SQL_EXECUTE_PATH = " ";
    public static final String SQL_QUERY_PATH = " ";

    /// /////////************* DATABASE *************////////////
    ///  ************* TABLE NAME *************//////
    public static final String CUSTOMER_TABLE_NAME = "customer";


    ///  ************* DATABASE NAME *************//////
    public static final String DEFAULT_DB = "test";


    ///  ************* SCHEMA NAME *************//////
    public static final String TEST_AUTOMATION_SCHEMA = "test_automation";

    /// /////////************* REQUEST MAPPING *************////////////
    public static final String API = "/api";
    public static final String USER_TYPE = "/user-type";
    public static final String USER_TYPE_LIST = "/user-type-list";
    public static final String UPDATE_USER_STATUS_TO_INACTIVE = "/update-user-status-to-inactive";

    /// /////////************* EnumConstants *************////////////
    public static final String AUTHORIZATION = "Authorization";
    public static final String RESPONSE_BODY = "Response Body: ";
    public static final String REQUSET_BODY = "Request Body: ";
    public static final String URL = "URL: ";

    /// /////////************* MESSAGE *************////////////
    public static final String CUSTOMER_NOT_FOUND_MESSAGE = "Customer with id  not found ";
    public static final String DEFAULT_MAIL = "@testotomasyon.com.tr";
    public static final String DEFAULT_NOT_CUSTOMER = "Customer entity not found";
}
