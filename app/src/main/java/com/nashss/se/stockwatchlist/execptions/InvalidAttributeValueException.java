package com.nashss.se.stockwatchlist.execptions;


public class InvalidAttributeValueException extends InvalidAttributeException {

    private static final long serialVersionUID = 2415884883869772884L;

    public InvalidAttributeValueException() {super();}

    public InvalidAttributeValueException(String message) {super(message);}

    public InvalidAttributeValueException(Throwable cause) {super(cause);}

    public InvalidAttributeValueException(String message, Throwable cause) {super(message,cause);}
}
