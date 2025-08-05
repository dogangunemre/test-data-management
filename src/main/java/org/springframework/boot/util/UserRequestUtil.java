package org.springframework.boot.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserRequestUtil {

    public static final String GET_MERNIS_CREATE_SQL_REQUEST = """
            {
                "sqlText": "INSERT INTO **** (id, identity_value, name, surname, birthdate)  VALUES ((select nextval('****')), '%s', '%s', '%s', '01-01-1990');",
                "dbName": "crm"
            }""";

}
