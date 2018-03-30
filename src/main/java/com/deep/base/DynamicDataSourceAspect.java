package com.deep.base;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * 通过aop，动态切换数据源
 * @author Administrator
 *
 */

@Aspect  
@Order(-1) //spring order排序后执行顺序是从小到大，目的是确保在事务管理器执行前先执行  
@Component  
public class DynamicDataSourceAspect {  
  
	@Before("@annotation(DS)")
    public void beforeSwitchDS(JoinPoint point){

        //获得当前访问的class
        Class<?> className = point.getTarget().getClass();

        //获得访问的方法名
        String methodName = point.getSignature().getName();
        //得到方法的参数的类型
        Class[] argClass = ((MethodSignature)point.getSignature()).getParameterTypes();
        String dataSource = DynamicDataSourceContextHolder.DEFAULT_DS;
        try {
            // 得到访问的方法对象
            Method method = className.getMethod(methodName, argClass);

            // 判断是否存在@DS注解
            if (method.isAnnotationPresent(DS.class)) {
                DS annotation = method.getAnnotation(DS.class);
                // 取出注解中的数据源名
                dataSource = annotation.value();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 切换数据源
        DynamicDataSourceContextHolder.setDataSourceKey(dataSource);

    }


    @After("@annotation(DS)")
    public void afterSwitchDS(JoinPoint point){

    	DynamicDataSourceContextHolder.clear();

    }
}  
