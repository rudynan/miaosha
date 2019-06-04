package com.rudy.miaosha.config;

import com.rudy.miaosha.domain.MXUser;
import com.rudy.miaosha.service.UserService;
import com.rudy.miaosha.service.impl.UserServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> parameterType = methodParameter.getParameterType();
        System.out.println(parameterType);
        return parameterType == MXUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);

        String paramToken = request.getParameter(UserServiceImpl.COOKIE);
        String cookieToken = getCookieValue(request,UserServiceImpl.COOKIE);
        if (StringUtils.isBlank(cookieToken) && StringUtils.isBlank(paramToken)){
            return null;
        }
        String token = StringUtils.isBlank(cookieToken)?paramToken:cookieToken;
        MXUser userByToken = userService.getUserByToken(response, token);
        return userByToken;
    }

    private String getCookieValue(HttpServletRequest request, String cookie) {

        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length <=0){
            return null;
        }
        for (Cookie cookievo : cookies) {
            if (StringUtils.equals(cookievo.getName(), cookie)){
                return cookievo.getValue();
            }
        }

        return null;
    }
}
