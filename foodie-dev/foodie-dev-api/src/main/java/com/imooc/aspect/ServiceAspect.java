package com.imooc.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {

    /**
     * AOP通知
     * 1.前置通知：在方法调用之前执行
     * 2.后置通知：在方法正常调用之后执行
     * 3.环绕通知：在方法调用前后，分别执行
     * 4.异常通知：如果在方法异常，则通知
     * 5.最终通知：在方法调用之后执行
     */

    final Logger log = LoggerFactory.getLogger(ServiceAspect.class);


    /**
     * 切面表达式：
     * execution：代表所要执行的表达式主体
     * 第一处 *代表方法返回的类型
     * 第二次 包名代表aop监控的类所在的包
     * 第三处 ..代表该包以及其子包下得所有类方法
     * 第四处 *代表类名，*代表所有类名
     * 第五处 *(..) *代表类中的方法名，(..) 表示方法中的所有参数
     * @param joinPoint
     * @return
     */
    @Around("execution(* com.imooc.service.impl..*.*(..))")
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("===开始执行 {}.{} ===",joinPoint.getTarget().getClass(),joinPoint.getSignature().getName());
        long begin = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();
        long takeTime = end - begin;
        if(takeTime>3000){
            log.error("===执行结束，耗时：{} 毫秒 ====",takeTime);
        }
        else if(takeTime>1500&&takeTime<=3000){
            log.error("===执行结束，耗时：{} 毫秒 ====",takeTime);
        }
        else{
            log.error("===执行结束，耗时：{} 毫秒 ====",takeTime);
        }
        return result;


    }
}
