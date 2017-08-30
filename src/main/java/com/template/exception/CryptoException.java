package com.template.exception;

/**
 * @author Created by Igor Zadyra at 4/15/2015
 * @author Last modified by $Author: apitenko@rightandabove.com $author $ <br>
 * @author Last modified on $Date: 2015-04-16 14:06:54 +0300 (Thu, 16 Apr 2015) $date $ at revision $Revision: 40 $revision $ <br>
 */
public class CryptoException extends Exception {

    public CryptoException() {
    }

    public CryptoException(String message) {
        super(message);
    }

    public CryptoException(String message, Throwable cause) {
        super(message, cause);
    }

}
