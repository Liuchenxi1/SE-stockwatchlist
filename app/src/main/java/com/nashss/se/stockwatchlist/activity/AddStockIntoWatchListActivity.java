package com.nashss.se.stockwatchlist.activity;

import com.nashss.se.stockwatchlist.activity.requests.AddStockIntoWatchListRequest;
import com.nashss.se.stockwatchlist.activity.results.AddStockIntoWatchListResult;
import com.nashss.se.stockwatchlist.converters.ModelConverter;
import com.nashss.se.stockwatchlist.dynamodb.WatchListDao;
import com.nashss.se.stockwatchlist.dynamodb.models.WatchList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AddStockIntoWatchListActivity {

    private final Logger log = LogManager.getLogger();

    private final WatchListDao watchListDao;

    public AddStockIntoWatchListActivity(WatchListDao watchListDao) {
        this.watchListDao = watchListDao;
    }

    public AddStockIntoWatchListResult handleRequest(final AddStockIntoWatchListRequest addStockIntoWatchListRequest) {
        log.info("Received AddSongToPlaylistRequest {} ", addStockIntoWatchListRequest);

        String stock = addStockIntoWatchListRequest.getStockSymbol();

        WatchList watchList = watchListDao.getWatchList(addStockIntoWatchListRequest.getWatchlistName());

        if (!watchList.getWatchlistName().equals(addStockIntoWatchListRequest.getWatchlistName())) {
            throw new SecurityException("You don't own this watchlist to add stock to it.");
        }

        watchList.getStockSymbols().add(stock);

        watchList = watchListDao.saveWatchList(watchList);

        new ModelConverter().toWatchListModel(watchList.getStockSymbols())

        return AddStockIntoWatchListResult.builder()
                .build();


    }

}
