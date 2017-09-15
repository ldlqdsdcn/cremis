package cn.cityre.mis.core.web.interceptor;

import cn.cityre.mis.core.web.annon.MisValid;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by 刘大磊 on 2017/9/5 14:48.
 */
@Aspect
@Component
public class ParameterValidatorInterceptor {
    @Before("execution(* *..controller.*.*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void controllerMethodPointcut(JoinPoint joinPoint) {
        System.out.println("我这是在拦截方法");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Annotation[][] methodAnnotations = method.getParameterAnnotations();
        if (methodAnnotations != null)
            {
                for (int i = 0; i < methodAnnotations.length; i++)
                {
                    Annotation[] annotations = methodAnnotations[i];
                    for (Annotation annotation : annotations) {
                        if (annotation instanceof MisValid) {
                            Object obj = joinPoint.getArgs()[i];
                            if (obj != null) {

                            }
                        }
                    }
                }
            }
    }


}
