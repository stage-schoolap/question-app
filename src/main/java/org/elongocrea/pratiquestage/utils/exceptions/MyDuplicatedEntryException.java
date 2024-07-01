package org.elongocrea.pratiquestage.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.CONFLICT)
public class MyDuplicatedEntryException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public MyDuplicatedEntryException() {
        super();
    }

    public MyDuplicatedEntryException(String message, Throwable cause, boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public MyDuplicatedEntryException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyDuplicatedEntryException(String message) {
        super(message);
    }

    public MyDuplicatedEntryException(Throwable cause) {
        super(cause);
    }
}
