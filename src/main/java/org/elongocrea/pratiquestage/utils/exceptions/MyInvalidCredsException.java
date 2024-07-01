package org.elongocrea.pratiquestage.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class MyInvalidCredsException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public MyInvalidCredsException() {
        super();
    }

    public MyInvalidCredsException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public MyInvalidCredsException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyInvalidCredsException(String message) {
        super(message);
    }

    public MyInvalidCredsException(Throwable cause) {
        super(cause);
    }
}
