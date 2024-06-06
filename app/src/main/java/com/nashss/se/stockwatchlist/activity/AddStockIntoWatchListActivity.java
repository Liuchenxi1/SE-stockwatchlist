package com.nashss.se.stockwatchlist.activity;

import com.nashss.se.stockwatchlist.activity.requests.AddStockIntoWatchListRequest;
import com.nashss.se.stockwatchlist.activity.results.AddStockIntoWatchListResult;
import com.nashss.se.stockwatchlist.converters.ModelConverter;
import com.nashss.se.stockwatchlist.dynamodb.WatchListDao;
import com.nashss.se.stockwatchlist.dynamodb.models.WatchList;
import com.nashss.se.stockwatchlist.models.WatchListModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

public class AddStockIntoWatchListActivity {

    private final Logger log = LogManager.getLogger();

    private final WatchListDao watchListDao;

    @Inject
    public AddStockIntoWatchListActivity(WatchListDao watchListDao) {
        this.watchListDao = watchListDao;
    }

    public AddStockIntoWatchListResult handleRequest(final AddStockIntoWatchListRequest addStockIntoWatchListRequest) {
        log.info("Received AddSongToPlaylistRequest {} ", addStockIntoWatchListRequest);

//        String stock = addStockIntoWatchListRequest.getStockSymbol();

        WatchList watchList = new WatchList();

        watchList.setUserEmail(addStockIntoWatchListRequest.getEmail());
        watchList.setWatchlistName(addStockIntoWatchListRequest.getWatchlistName());

        watchList = watchListDao.getWatchList(addStockIntoWatchListRequest.getWatchlistName());

        if (!watchList.getWatchlistName().equals(addStockIntoWatchListRequest.getWatchlistName())) {
            throw new SecurityException("You don't own this watchlist to add stock to it.");
        }

        watchList.getStockSymbols().add(addStockIntoWatchListRequest.getStockSymbol());

        watchList = watchListDao.saveWatchList(watchList);

        WatchListModel watchListModel = new ModelConverter().toWatchListModel(watchList);

        return AddStockIntoWatchListResult.builder()
                .withWatchListModel(watchListModel)
                .build();

    }

}
