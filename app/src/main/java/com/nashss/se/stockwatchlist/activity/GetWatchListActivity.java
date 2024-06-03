package com.nashss.se.stockwatchlist.activity;


import com.nashss.se.stockwatchlist.activity.requests.GetWatchListRequest;
import com.nashss.se.stockwatchlist.activity.results.GetWatchListResult;
import com.nashss.se.stockwatchlist.converters.ModelConverter;
import com.nashss.se.stockwatchlist.dynamodb.WatchListDao;

import com.nashss.se.stockwatchlist.dynamodb.models.WatchList;
import com.nashss.se.stockwatchlist.execptions.WatchlistIsNotFoundException;
import com.nashss.se.stockwatchlist.models.WatchListModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;



public class GetWatchListActivity {

    private final Logger log = LogManager.getLogger();

    private final WatchListDao watchListDao;

    @Inject
    public GetWatchListActivity(WatchListDao watchListDao) {
        this.watchListDao = watchListDao;
    }

    public GetWatchListResult handleRequest (final GetWatchListRequest getWatchListRequest) {
        log.info("Received GetWatchListResult {}", getWatchListRequest);

        WatchList result = new WatchList();
        result.setUserEmail(getWatchListRequest.getEmail());
        result.setWatchlistName(getWatchListRequest.getWatchlistName());

        WatchList watchListToLoad = watchListDao.getWatchlist(result);

        WatchListModel listToLoad = new ModelConverter().toWatchListModel(watchListToLoad);

        return GetWatchListResult.builder()
                .withGetWatchList(listToLoad)
                .build();
    }
}
