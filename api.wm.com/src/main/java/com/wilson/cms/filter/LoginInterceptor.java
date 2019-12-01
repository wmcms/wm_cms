package com.wilson.cms.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wilson.cms.annotation.AllowAnonymous;
import com.wilson.cms.annotation.Permission;
import com.wilson.cms.po.UserAccountPo;
import com.wilson.cms.service.UserService;
import com.wilson.cms.utils.JsonUtils;
import com.wilson.cms.utils.RedisUtils;
import com.wilson.cms.utils.StringUtils;
import com.wilson.cms.vo.Result;

public class LoginInterceptor implements HandlerInterceptor{

    private static Logger logger= LoggerFactory.getLogger(HandlerInterceptor.class);

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        @SuppressWarnings("unused")
		boolean isAuth=true;
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取方法上的注解
            AllowAnonymous allowAnonymous = handlerMethod.getMethod().getAnnotation(AllowAnonymous.class);
            if (allowAnonymous==null){
                allowAnonymous = handlerMethod.getMethod().getDeclaringClass().getAnnotation(AllowAnonymous.class);
                if (allowAnonymous==null) {
                    //如果没有标记了注解，则判登录
                    String token = request.getHeader("x-api-token");
                    if(StringUtils.isEmpty(token)){
                        responseJson(response,Result.Error("请先登录"));
                        return false;
                    }
                    else {
                        UserAccountPo user = (UserAccountPo) RedisUtils.get(token);
                        if(user==null){
                            responseJson(response,Result.Error("请先登录"));
                            return false;
                        }
                        request.setAttribute("userId",user.getUserId());
                        request.setAttribute("token",token);
                        logger.debug("执行了权限认证，token="+token);
                        Permission permission = handlerMethod.getMethod().getAnnotation(Permission.class);
                        if (permission == null) {
                            permission = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Permission.class);
                        }
                        if (permission != null && !StringUtils.isEmpty(permission.value())) {
                            //如果标记了注解，则判断权限
                            @SuppressWarnings("unused")
							String pv=permission.value();
                            responseJson(response,Result.Error("请先登录"));
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    private void responseJson(HttpServletResponse response,  Result result) throws Exception{
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JsonUtils.obj2String(result));

        } catch (IOException e) {
          //  logger.error("response error",e);
        } finally {
            if (writer != null)
                writer.close();
            response.flushBuffer();
        }
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // TODO
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // TODO
    }
}
