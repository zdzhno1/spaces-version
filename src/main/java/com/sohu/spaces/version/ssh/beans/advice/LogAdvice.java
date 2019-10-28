package com.sohu.spaces.version.ssh.beans.advice;

import com.sohu.spaces.version.ssh.utils.LogUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Aspect
public class LogAdvice {

    @Pointcut("execution(* com.sohu.spaces.version.ssh.service.impl.AccountServiceImpl.saveAccount(..))")
    private void pc1(){}

    public void before(){
        storeCurrentTime();
    }

    public void afterReturning(){
        storeCurrentTime();

    }

    public void after(){

        LogUtils.timesTl.remove();
    }

    @Around("pc1()")
    public Object around(ProceedingJoinPoint pjp){
        try {
            before();
            Object obj = pjp.proceed();
            afterReturning();
            List<LocalDateTime> list = LogUtils.timesTl.get();
            if (list!=null&&list.size()==2) {
                Duration between = Duration.between(list.get(0), list.get(1));
                System.out.println(pjp.getSignature().getName() + "的执行时间为：" +between.toMillis());
            }
            return obj;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            after();
        }
        return null;
    }




    private void storeCurrentTime() {
        List<LocalDateTime> list = LogUtils.timesTl.get();
        if (list == null) {
            list = new ArrayList<>();
            LogUtils.timesTl.set(list);
        }
        list.add(LocalDateTime.now());
    }
}
