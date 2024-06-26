package com.nashss.se.stockwatchlist.activity;

import com.nashss.se.stockwatchlist.activity.requests.GetWatchListsRequest;
import com.nashss.se.stockwatchlist.activity.results.GetWatchListsResult;
import com.nashss.se.stockwatchlist.converters.ModelConverter;
import com.nashss.se.stockwatchlist.dynamodb.WatchListDao;
import com.nashss.se.stockwatchlist.dynamodb.models.WatchList;
import com.nashss.se.stockwatchlist.models.WatchListModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class GetWatchListsActivity {

    private final Logger log = LogManager.getLogger();

    private final WatchListDao watchListDao;

    @Inject
    public GetWatchListsActivity(WatchListDao watchListDao) {
        this.watchListDao = watchListDao;
    }

    public GetWatchListsResult handleRequest(final GetWatchListsRequest getWatchListsRequest) {
        log.info("Received GetWatchListsRequest {}", getWatchListsRequest);

        String email = getWatchListsRequest.getEmail();
        if (email == null) {
            log.error("Email is null in GetWatchListsRequest");
            throw new RuntimeException("Email is null in GetWatchListsRequest");
        }
        log.info("Fetching watch lists for email: {}", email);

        List<WatchList> watchLists = watchListDao.getWatchListsByEmail(email);

        if (watchLists == null || watchLists.isEmpty()) {
            watchLists = new ArrayList<>();
        }

        List<WatchListModel> watchListModels = new ModelConverter().toWatchlistModels(watchLists);

        return GetWatchListsResult.builder()
                .withWatchLists(watchListModels)
                .build();
    }
}