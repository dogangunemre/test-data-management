package org.springframework.boot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * DynamicRoutingDataSource.
 */
@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        String key = DataSourceContextHolder.getDataSourceType();
        return key;
    }

    public Map<Object, DataSource> getTargetDataSources() {
        return super.getResolvedDataSources();
    }

    public void close() throws Exception {
        Map<Object, DataSource> targetDataSources = getTargetDataSources();
        if (targetDataSources != null) {
            for (DataSource targetDataSource : targetDataSources.values()) {
                if (targetDataSource instanceof AutoCloseable) {
                    ((AutoCloseable) targetDataSource).close();
                }
            }
        }
    }
}