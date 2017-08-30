package com.template.handlers;

import com.template.domain.repository.UserRepository;
import com.template.security.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Created by izadira@rightandabove.com at 6/15/2015
 * @author Last modified by $Author: $author $ <br>
 * @author Last modified on $Date: $date $ at revision $Revision: $revision $ <br>
 */
@Component
public class LogoutSuccessHandler extends SecurityContextLogoutHandler {

    private static final Logger LOG = LoggerFactory.getLogger(LogoutSuccessHandler.class);
    private static final String anonymousUserName = "anonymousUser";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageSource messageSource;

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {

        if (authentication == null || authentication.getPrincipal() == null || authentication.getPrincipal().equals(anonymousUserName))
            return;

        String email = ((UserDetails) authentication.getPrincipal()).getEmail();

        if (email == null)
            return;

//        User user = userRepository.findByEmail(email);

//        if (user == null) {
//            LOG.error(getMessage("auth-failure.bad-email", new String[]{email}));
//            return;
//        }
//
//        user.setOnline(false);
//        userRepository.save(user);
    }

    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

}
