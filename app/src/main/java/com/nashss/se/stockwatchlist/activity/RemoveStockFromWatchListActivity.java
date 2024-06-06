package com.nashss.se.stockwatchlist.activity;


import com.nashss.se.stockwatchlist.activity.requests.AddStockIntoWatchListRequest;
import com.nashss.se.stockwatchlist.activity.requests.RemoveStockFromWatchListRequest;
import com.nashss.se.stockwatchlist.activity.results.RemoveStockFromWatchListResult;
import com.nashss.se.stockwatchlist.converters.ModelConverter;
import com.nashss.se.stockwatchlist.dynamodb.WatchListDao;
import com.nashss.se.stockwatchlist.dynamodb.models.WatchList;
import com.nashss.se.stockwatchlist.models.WatchListModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

public class RemoveStockFromWatchListActivity {

    private final Logger log = LogManager.getLogger();

    private final WatchListDao watchListDao;

    @Inject

    public RemoveStockFromWatchListActivity(WatchListDao watchListDao) {
        this.watchListDao = watchListDao;
    }

    public RemoveStockFromWatchListResult handleRequest (final RemoveStockFromWatchListRequest removeStockFromWatchListRequest) {
        log.info("Received remove stock from List request {} ", removeStockFromWatchListRequest);

        String stock = removeStockFromWatchListRequest.getStockSymbol();

        WatchList toUpdate = new WatchList();
        toUpdate.setUserEmail(removeStockFromWatchListRequest.getEmail());
        toUpdate.setWatchlistName(removeStockFromWatchListRequest.getWatchlistName());

        WatchList updateWatchList = watchListDao.getWatchlist(toUpdate);

        if (updateWatchList == null ) {
            throw new SecurityException("You don't own this watchlist to remove stock to it.");
        }

        List<String> list = updateWatchList.getStockSymbols();
        list.remove(stock);

        log.info("the list is: {}", list.size());

        toUpdate.setStockSymbols(list);

        WatchList updatedWatchList = watchListDao.saveWatchList(toUpdate);

        WatchListModel watchListModel = new ModelConverter().toWatchListModel(updatedWatchList);

        return RemoveStockFromWatchListResult.builder()
                .withWatchListModel(watchListModel)
                .build();
    }

}
