package com.template.exception;

/**
 * @author Created by Igor Zadyra at 4/14/2015
 * @author Last modified by $Author: apitenko@rightandabove.com $author $ <br>
 * @author Last modified on $Date: 2015-04-16 14:06:54 +0300 (Thu, 16 Apr 2015) $date $ at revision $Revision: 40 $revision $ <br>
 */
public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException() {
    }

    public AlreadyExistsException(String message) {
        super(message);
    }

    public AlreadyExistsException(Class objectType) {
        super(objectType.getSimpleName() + " already exists.");
    }

}
