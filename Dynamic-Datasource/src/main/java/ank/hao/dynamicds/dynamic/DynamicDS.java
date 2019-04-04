package ank.hao.dynamicds.dynamic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Slf4j
public class DynamicDS extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        log.info("当前数据源是："+DataSourceContextHolder.getDB());
        return DataSourceContextHolder.getDB();
    }
}
