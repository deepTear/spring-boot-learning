package com.deep.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/**
 * ��̬��ȡ����Դ
 * @author Administrator
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	
    private static final Logger log = LoggerFactory.getLogger(DynamicDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
        log.debug("����ԴΪ{}", DynamicDataSourceContextHolder.getDataSourceKey());

        return DynamicDataSourceContextHolder.getDataSourceKey();
    }
    
    

}
	