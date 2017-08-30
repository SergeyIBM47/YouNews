package com.template.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Created by Igor Zadyra at 4/15/2015
 * @author Last modified by $Author: apitenko@rightandabove.com $author $ <br>
 * @author Last modified on $Date: 2015-04-22 15:03:57 +0300 (Wed, 22 Apr 2015) $date $ at revision $Revision: 92 $revision $ <br>
 *         <p/>
 *         Description: AuthenticationFailureHandler which performs a redirect to the value of the {@link #getDefaultFailureUrl
 *         defaultFailureUrl} property when the onAuthenticationFailure method is called.
 *         If the property has not been set it will send a 401 response to the client, with the error message from the
 *         AuthenticationException which caused the failure.
 */
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private static final Logger LOG = LoggerFactory.getLogger(CustomAuthenticationFailureHandler.class);
    private static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";

    private String defaultFailureUrl;
    private String changePasswordUrl;

    @Autowired
    private MessageSource messageSource;

    public CustomAuthenticationFailureHandler(String defaultFailureUrl, String changePasswordUrl) {
        Assert.notNull(defaultFailureUrl);
        Assert.notNull(changePasswordUrl);
        this.defaultFailureUrl = defaultFailureUrl;
        this.changePasswordUrl = changePasswordUrl;
    }

    public String getDefaultFailureUrl() {
        return defaultFailureUrl;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        LOG.error("Auth failure: {} msg={}", exception.getClass().getSimpleName(), exception.getMessage());

        HttpSession session = request.getSession();
        String url;
        if (exception instanceof CredentialsExpiredException) {
            url = changePasswordUrl;
            session.setAttribute("expired", true);
            session.setAttribute("username", request.getParameter(SPRING_SECURITY_FORM_USERNAME_KEY));
        } else if (exception instanceof BadCredentialsException) {
            url = defaultFailureUrl;
            session.setAttribute("error", getMessage("login.failed",new String[]{""}));
            session.setAttribute("username", request.getParameter(SPRING_SECURITY_FORM_USERNAME_KEY));
        }else {
            url = defaultFailureUrl;
            session.setAttribute("error", exception.getMessage());
            session.setAttribute("username", request.getParameter(SPRING_SECURITY_FORM_USERNAME_KEY));
        }

        response.sendRedirect(url);
    }

    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

}
