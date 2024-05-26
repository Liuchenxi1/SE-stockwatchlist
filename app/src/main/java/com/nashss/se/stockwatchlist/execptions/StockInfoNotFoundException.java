package com.nashss.se.stockwatchlist.execptions;

import java.io.IOException;

public class StockInfoNotFoundException extends IOException {
    private static final long serialVersionUID = -7948825759814973176L;

    private StockInfoNotFoundException() { super(); }

    private StockInfoNotFoundException(String message) { super(message); }

    private StockInfoNotFoundException(Throwable cause) { super(cause); }

    private StockInfoNotFoundException(String message, Throwable cause) {super(message, cause);}

}
