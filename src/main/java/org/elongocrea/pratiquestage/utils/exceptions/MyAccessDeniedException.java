package org.elongocrea.pratiquestage.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class MyAccessDeniedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public MyAccessDeniedException() {
        super();
    }

    public MyAccessDeniedException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public MyAccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyAccessDeniedException(String message) {
        super(message);
    }

    public MyAccessDeniedException(Throwable cause) {
        super(cause);
    }
}
