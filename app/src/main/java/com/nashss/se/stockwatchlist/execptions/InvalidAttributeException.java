package com.nashss.se.stockwatchlist.execptions;

public class InvalidAttributeException extends RuntimeException {
    private static final long serialVersionUID = 6122213751326675708L;

    public InvalidAttributeException() {
        super();
    }

    public InvalidAttributeException(String message) {
        super(message);
    }

    public InvalidAttributeException(Throwable cause) {
        super(cause);
    }

    public InvalidAttributeException(String message, Throwable cause) {
        super(message, cause);
    }
}


