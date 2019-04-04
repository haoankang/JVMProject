package ank.hao.dynamicds.dynamic;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class DynamicDSAspect {

    @Before("@annotation(DDS)")
    public void beforeswitchDS(JoinPoint joinPoint){
        String datasource = DataSourceContextHolder.DEFAULT_DS;

        Class<?> className = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();
        Class[] argClass = ((MethodSignature)joinPoint.getSignature()).getParameterTypes();

        try {
            Method method = className.getDeclaredMethod(methodName, argClass);
            if(method.isAnnotationPresent(DDS.class)){
                DDS dds = method.getAnnotation(DDS.class);
                datasource = dds.value();
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }

        DataSourceContextHolder.setDB(datasource);
    }

    @After("@annotation(DDS)")
    public void afterswitchDS(JoinPoint joinPoint){
        DataSourceContextHolder.clearDB();
    }
}
