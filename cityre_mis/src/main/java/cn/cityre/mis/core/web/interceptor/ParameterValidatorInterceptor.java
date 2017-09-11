package cn.cityre.mis.core.web.interceptor;

import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.Method;

/**
 * Created by 刘大磊 on 2017/9/5 14:48.
 */
public class ParameterValidatorInterceptor extends  HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            Object target = hm.getBean();
            Class<?> clazz = hm.getBeanType();
            Method m = hm.getMethod();
            MethodParameter[] parameters= hm.getMethodParameters();
            if(parameters!=null)
            {
                for(MethodParameter parameter:parameters)
                {

                }
            }
        }

        return true;
    }
}
