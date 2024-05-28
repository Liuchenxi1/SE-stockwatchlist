package com.nashss.se.stockwatchlist.execptions;

import java.io.IOException;

public class StockInfoNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -7948825759814973176L;

    public StockInfoNotFoundException() { super(); }

    public StockInfoNotFoundException(String message) { super(message); }

    public StockInfoNotFoundException(Throwable cause) { super(cause); }

    public StockInfoNotFoundException(String message, Throwable cause) {super(message, cause);}

}
