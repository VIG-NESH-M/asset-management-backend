package com.staunch.tech.interceptor;


import com.staunch.tech.utils.JwtUtils;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyHandlerInterceptor implements HandlerInterceptor {

    private JwtUtils jwtUtils= new JwtUtils();
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object object, Exception arg3) throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                                Object object, ModelAndView model) throws Exception {
    }

   @Override
   public boolean preHandle(HttpServletRequest request,
   HttpServletResponse response, Object handler) throws Exception {
       return true;
   }
}







//if ( (!request.getRequestURL().toString().contains("authenticate/login"))
//&& (request.getHeader("Authorization") == null ||
//jwtUtils.isInvalid(request.getHeader("Authorization")))) {
//response.getWriter().write("Authorization Token is Null or Invalid");
//response.setStatus(HttpStatus.UNAUTHORIZED.value());
//return false;
//}