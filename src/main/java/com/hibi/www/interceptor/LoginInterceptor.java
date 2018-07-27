package com.hibi.www.interceptor;

import com.hibi.www.interfaces.Auth;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.method.HandlerMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Optional;

import static com.hibi.www.tools.Constants.USER_CODE_SESSION_KEY;


@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    private final static String SESSION_KEY_PREFIX = "session:";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String user = (String) request.getSession().getAttribute("user");
        if (Optional.ofNullable(user).map(String::length).orElse(0)>0){
            return true;
        }
        if (!handler.getClass().isAssignableFrom(HandlerMethod.class)){
            return true;
        }
        final HandlerMethod handlerMethod = (HandlerMethod) handler;
//        System.out.print(request.getContextPath()+"----------------");
        final Method method = handlerMethod.getMethod();
                 final Class<?> clazz = method.getDeclaringClass();
                 if (clazz.isAnnotationPresent(Auth.class) || method.isAnnotationPresent(Auth.class)) {
                         if (request.getAttribute(USER_CODE_SESSION_KEY) == null) {
                                 response.sendRedirect(request.getContextPath() + "login");
                                 return false;
                             } else {
                                 return true;
                             }
                     }
                 return true;
    }
}
