package com.deep.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//DynmicDataSourceContextHolder���������õ�ǰʹ�õ�����ԴKey
public class DynamicDataSourceContextHolder {  
	
	public static final Logger log = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);
	
	/**
     * Ĭ������Դ
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
