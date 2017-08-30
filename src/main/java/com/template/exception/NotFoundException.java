package com.template.exception;

/**
 * @author Created by dtimchenko at 14/04/2015
 * @author Last modified by $Author: apitenko@rightandabove.com $author $ <br>
 * @author Last modified on $Date: 2015-04-16 13:07:01 +0300 (Thu, 16 Apr 2015) $date $ at revision $Revision: 37 $revision $ <br>
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Class objectType) {
        super(objectType.getSimpleName() + " not found.");
    }

}
