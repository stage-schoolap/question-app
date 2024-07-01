package org.elongocrea.pratiquestage.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.CONFLICT)
public class MyInvalidEntryException extends RuntimeException {

    @Serial
    private static final long serialVersionUID =1L;

    public MyInvalidEntryException() {
        super();
    }

    public MyInvalidEntryException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public MyInvalidEntryException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyInvalidEntryException(String message) {
        super(message);
    }

    public MyInvalidEntryException(Throwable cause) {
        super(cause);
    }
}
