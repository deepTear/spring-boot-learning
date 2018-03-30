package com.deep.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/**
 * 动态获取数据源
 * @author Administrator
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	
    private static final Logger log = LoggerFactory.getLogger(DynamicDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
        log.debug("数据源为{}", DynamicDataSourceContextHolder.getDataSourceKey());

        return DynamicDataSourceContextHolder.getDataSourceKey();
    }
    
    

}
	