package com.nashss.se.stockwatchlist.converters;


import com.nashss.se.stockwatchlist.dynamodb.models.WatchList;
import com.nashss.se.stockwatchlist.models.WatchListModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts between Data and API models.
 */
public class ModelConverter {
    /**
     * Converts a provided {@link Watchlist} into a {@link WatchListModel} representation.
     *
     * @param watchlist the watchlist to convert
     * @return the converted watchlist
     */
    public WatchListModel toWatchListModel(WatchList watchList) {
//        List<String> stockSymbols = new ArrayList<>();
//        if (watchList.getStockSymbols() != null) {
//            stockSymbols = new ArrayList<>(watchList.getStockSymbols());
//        }

        return WatchListModel.builder()
                .withEmail(watchList.getUserEmail())
                .withWatchlistName(watchList.getWatchlistName())
                .withStockSymbols(watchList.getStockSymbols())
                .build();
    }

    //TODO need more!!


}

