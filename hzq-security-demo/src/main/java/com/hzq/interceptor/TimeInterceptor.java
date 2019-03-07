package com.hzq.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 自定义拦截器
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {

    /**
     * 在controller方法被调用之前被调用
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("preHandle");

        System.out.println(((HandlerMethod)o).getBean().getClass().getName());
        System.out.println(((HandlerMethod)o).getMethod().getName());
        httpServletRequest.setAttribute("startTime",System.currentTimeMillis());
        return true;
    }

    /**
     * 在controller方法被调用之后被调用
     * 在方法抛出异常时  此方法不调用
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");

        long startTime = (long) httpServletRequest.getAttribute("startTime");
        System.out.println("time filter"+String.valueOf(System.currentTimeMillis()-startTime));
    }

    /**
     * 在controller方法被调用不论 正常 异常 都会被调用
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion");
        long startTime = (long) httpServletRequest.getAttribute("startTime");
        System.out.println("time filter"+String.valueOf(System.currentTimeMillis()-startTime));
        System.out.println("e = " + e);
    }
}
