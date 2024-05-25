package com.nashss.se.stockwatchlist.execptions;


import com.nashss.se.stockwatchlist.dynamodb.models.WatchList;

public class WatchlistIsNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 8020456049233634167L;

    public WatchlistIsNotFoundException () {super();}

    public WatchlistIsNotFoundException (String message) {super(message);}

    public WatchlistIsNotFoundException (Throwable cause) {super(cause);}

    public WatchlistIsNotFoundException (String message, Throwable cause) {super(message, cause);}

}
