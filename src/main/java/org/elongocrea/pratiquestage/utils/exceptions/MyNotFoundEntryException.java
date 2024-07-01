package org.elongocrea.pratiquestage.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyNotFoundEntryException extends RuntimeException {

    private static final long serialVersionUID =1L;

    public MyNotFoundEntryException() {
        super();
    }

    public MyNotFoundEntryException(String message, Throwable cause, boolean enableSuppression,
                                    boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public MyNotFoundEntryException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyNotFoundEntryException(String message) {
        super(message);
    }

    public MyNotFoundEntryException(Throwable cause) {
        super(cause);
    }
}
