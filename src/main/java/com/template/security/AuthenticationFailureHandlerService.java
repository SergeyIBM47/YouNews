package com.template.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by: Sergey Volokh
 * Date: 6/8/2016
 * Time: 12:06 PM
 * Project: springmvcs
 */
@Component
public class AuthenticationFailureHandlerService implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        System.out.println("LOGIN FAIL!!!");
        httpServletResponse.sendRedirect("/logins?error");
    }
}
