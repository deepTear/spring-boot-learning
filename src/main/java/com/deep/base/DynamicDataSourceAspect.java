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
 * ͨ��aop����̬�л�����Դ
 * @author Administrator
 *
 */

@Aspect  
@Order(-1) //spring order�����ִ��˳���Ǵ�С����Ŀ����ȷ�������������ִ��ǰ��ִ��  
@Component  
public class DynamicDataSourceAspect {  
  
	@Before("@annotation(DS)")
    public void beforeSwitchDS(JoinPoint point){

        //��õ�ǰ���ʵ�class
        Class<?> className = point.getTarget().getClass();

        //��÷��ʵķ�����
        String methodName = point.getSignature().getName();
        //�õ������Ĳ���������
        Class[] argClass = ((MethodSignature)point.getSignature()).getParameterTypes();
        String dataSource = DynamicDataSourceContextHolder.DEFAULT_DS;
        try {
            // �õ����ʵķ�������
            Method method = className.getMethod(methodName, argClass);

            // �ж��Ƿ����@DSע��
            if (method.isAnnotationPresent(DS.class)) {
                DS annotation = method.getAnnotation(DS.class);
                // ȡ��ע���е�����Դ��
                dataSource = annotation.value();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // �л�����Դ
        DynamicDataSourceContextHolder.setDataSourceKey(dataSource);

    }


    @After("@annotation(DS)")
    public void afterSwitchDS(JoinPoint point){

    	DynamicDataSourceContextHolder.clear();

    }
}  
