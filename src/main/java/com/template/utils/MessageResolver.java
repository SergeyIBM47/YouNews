package com.template.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * @author Created by Igor Zadyra at 4/14/2015
 * @author Last modified by $Author: apitenko@rightandabove.com $author $ <br>
 * @author Last modified on $Date: 2015-04-16 13:07:01 +0300 (Thu, 16 Apr 2015) $date $ at revision $Revision: 37 $revision $ <br>
 * <p>
 * Description:
 * Super class providing capabilities for resolving localized resource messages
 * May be extended by controller and service classes and called from within the methods
 * which have a HttpServletRequest context
 */

public abstract class MessageResolver {

    @Autowired
    private MessageSource messageSource;

    /**
     * Do the same as MessageResolver but uses current locale
     *
     * @param code - the code to lookup up, such as 'calculator.noRateSet'
     * @param args - Array of arguments that will be filled in for params within
     *             the message (params look like "{0}", "{1,date}", "{2,time}" within a message), or null if none
     * @return the resolved message
     * @see org.springframework.context.MessageSource
     */
    protected String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

    protected String getMessage(String code, Object arg) {
        return messageSource.getMessage(code, new Object[]{arg}, LocaleContextHolder.getLocale());
    }

    /**
     * Do the same as MessageResolver but uses current locale
     *
     * @param code - the code to lookup up, such as 'calculator.noRateSet'
     * @return the resolved message
     * @see org.springframework.context.MessageSource
     */
    protected String getMessage(String code) {
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }

}
