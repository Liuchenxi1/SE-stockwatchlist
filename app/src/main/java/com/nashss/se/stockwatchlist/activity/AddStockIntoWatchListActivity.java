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
        log.info("Received add stock into list request {} ", addStockIntoWatchListRequest);

        String stock = addStockIntoWatchListRequest.getStockSymbol();

        WatchList toUpdate = new WatchList();
        toUpdate.setUserEmail(addStockIntoWatchListRequest.getEmail());
        toUpdate.setWatchlistName(addStockIntoWatchListRequest.getWatchlistName());

       WatchList updateWatchList = watchListDao.getWatchlist(toUpdate);

        if (updateWatchList == null ) {
            throw new SecurityException("You don't own this watchlist to add stock to it.");
        }

        List<String> list = updateWatchList.getStockSymbols();
        list.add(stock);

        log.info("the list is: {}", list.size());

        toUpdate.setStockSymbols(list);

        WatchList updatedWatchList = watchListDao.saveWatchList(toUpdate);

        WatchListModel watchListModel = new ModelConverter().toWatchListModel(updatedWatchList);

        return AddStockIntoWatchListResult.builder()
                .withWatchListModel(watchListModel)
                .build();

    }

}
