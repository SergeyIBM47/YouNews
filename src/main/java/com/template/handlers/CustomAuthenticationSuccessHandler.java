package com.template.handlers;

import com.template.domain.model.User;
import com.template.domain.repository.UserRepository;
import com.template.security.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Created by izadira@rightandabove.com at 6/15/2015
 * @author Last modified by $Author: $author $ <br>
 * @author Last modified on $Date: $date $ at revision $Revision: $revision $ <br>
 */
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger LOG = LoggerFactory.getLogger(CustomAuthenticationFailureHandler.class);
    private static final String SPRING_SECURITY_USERNAME_KEY = "authUserEmail";
    private static final String anonymousUserName = "anonymousUser";

    private String successLoginUrl;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageSource messageSource;


    public CustomAuthenticationSuccessHandler(String successLoginUrl) {
        Assert.notNull(successLoginUrl);
        this.successLoginUrl = successLoginUrl;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        if (authentication == null || authentication.getPrincipal() == null || authentication.getPrincipal().equals(anonymousUserName))
            return;

        String email = ((UserDetails) authentication.getPrincipal()).getEmail();

        if (email == null)
            return;

        User user = userRepository.findByEmail(email);

        if (user == null) {
            LOG.error(getMessage("auth-failure.bad-email", new String[]{email}));
            return;
        }

        userRepository.save(user);

        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(SPRING_SECURITY_USERNAME_KEY, ((UserDetails) authentication.getPrincipal()).getEmail());

        httpServletResponse.sendRedirect(successLoginUrl);
    }

    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

}
