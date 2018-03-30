package com.deep.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//DynmicDataSourceContextHolder，用于设置当前使用的数据源Key
public class DynamicDataSourceContextHolder {  
	
	public static final Logger log = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);
	
	/**
     * 默认数据源
     */
    public static final String DEFAULT_DS = "datasource1";
	
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();  
  
    public static String getDataSourceKey() {  
        return contextHolder.get();  
    }  
  
    public static void setDataSourceKey(String dataSourcekey) {  
        contextHolder.set(dataSourcekey);  
    }  
    
    public static void clear() {  
        contextHolder.remove();  
    }  
}  
