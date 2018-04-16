package cn.com.aspect;

import cn.com.annotation.MyLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class MyAspect {

    @Pointcut(value = "execution(* cn.com.*.*(..))")
    private void myPointCut() {
    }

    @Before("myPointCut() &&  @annotation(log)")
    public void myBefore(JoinPoint joinPoint, MyLog log) {
        System.out.println("前置通知 : " + joinPoint.getSignature().getName());
    }

    // 该表达式指定com/annotation目录下及其所有子目录下的所有带有@MyLog注解的方法体为切点。
    @After("myPointCut() &&  @annotation(log)")
    public void myAfter(JoinPoint joinPoint, MyLog log) throws NoSuchMethodException, SecurityException {
        saveLog(log);
    }

    @Around(value = "myPointCut() &&  @annotation(log)")
    public Object myAround(ProceedingJoinPoint joinPoint, MyLog log) throws Throwable {
        System.out.println("前");
        // 手动执行目标类
        Object obj = joinPoint.proceed();

        System.out.println("后");
        return obj;
    }

    public void myAfterReturning(JoinPoint joinPoint, Object ret) {
        System.out.println("后置通知 : " + ret);
    }

    public void myAfterThrowing(JoinPoint joinPoint, Throwable e) {
        System.out.println("抛出异常 : " + e.getMessage());
    }

    private void printLog(JoinPoint joinPoint) throws NoSuchMethodException,
            SecurityException {
        MyLog log = null;
        Class targetClass = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class[] parameterTypes = null;
        if (arguments != null) {
            int length = arguments.length;
            parameterTypes = new Class[length];

            for (int i = 0; i < length; ++i) {
                parameterTypes[i] = arguments[i].getClass();
            }
        }

        // 使用反射获取注解内容
        Method method = targetClass.getMethod(methodName, parameterTypes);
        if (method != null) {
            log = method.getAnnotation(MyLog.class);
        }
        if (log != null) {
            System.out.println("====log======" + log.value());
            if (log.value() == 110) {
                System.out.println("日志注解中的值是110");
            }
            saveLog(log);
        }
    }

    private void saveLog(MyLog log) {
        System.out.println("插入日志记录");
        System.out.println();
    }
}
