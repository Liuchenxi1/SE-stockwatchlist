package com.nashss.se.stockwatchlist.activity;

import com.nashss.se.stockwatchlist.activity.requests.CreateWatchListRequest;
import com.nashss.se.stockwatchlist.activity.results.CreateWatchListResult;
import com.nashss.se.stockwatchlist.converters.ModelConverter;
import com.nashss.se.stockwatchlist.dynamodb.WatchListDao;
import com.nashss.se.stockwatchlist.dynamodb.models.WatchList;
import com.nashss.se.stockwatchlist.execptions.InvalidAttributeValueException;
import com.nashss.se.stockwatchlist.models.WatchListModel;
import com.nashss.se.stockwatchlist.utils.watchlistServiceUtils;

import javax.inject.Inject;

import org.apache.logging.log4j.*;

import java.util.ArrayList;

public class CreateWatchListActivity {
    private final Logger logger = LogManager.getLogger();
    private final WatchListDao watchListDao;

    @Inject
    public CreateWatchListActivity(WatchListDao watchListDao) {
        this.watchListDao = watchListDao;
    }

    public CreateWatchListResult handleRequest(final CreateWatchListRequest createWatchListRequest) {
        logger.info("Received CreateWatchListRequest {}", createWatchListRequest);

//        if (!watchlistServiceUtils.isValidString(createWatchListRequest.getEmail())) {
//            throw new InvalidAttributeValueException("The email:" + createWatchListRequest.getEmail() + "contains illegal characters");
//        }
//
//        if (!watchlistServiceUtils.isValidString(createWatchListRequest.getWatchlistName())){
//            throw new InvalidAttributeValueException("The Watchlist:" + createWatchListRequest.getWatchlistName() + "contains illegal characters");
//        }

        WatchList newWatchList = new WatchList();

        newWatchList.setUserEmail(createWatchListRequest.getEmail());
        newWatchList.setWatchlistName(createWatchListRequest.getWatchlistName());
        newWatchList.setStockSymbols(new ArrayList<>());

        WatchList result = watchListDao.saveWatchList(newWatchList);

        WatchListModel watchListModel = new ModelConverter().toWatchListModel(result);

        return CreateWatchListResult.builder()
                .withWatchListModel(watchListModel)
                .build();
    }

}
