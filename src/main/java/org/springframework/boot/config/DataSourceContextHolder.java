package org.springframework.boot.config;

import lombok.Data;

/**
 * DataSourceContextHolder.
 */
@Data
public class DataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();
    private static final String DEFAULT_DATA_SOURCE_TYPE = "test";

    public static String getDataSourceType() {
        String dataSourceType = contextHolder.get();
        return dataSourceType != null ? dataSourceType : DEFAULT_DATA_SOURCE_TYPE;
    }


    public static void setDataSourceType(String dataSourceType) {
        contextHolder.set(dataSourceType != null ? dataSourceType : DEFAULT_DATA_SOURCE_TYPE);
    }

    public static void clearDataSourceType() {
        contextHolder.remove();
    }
}
