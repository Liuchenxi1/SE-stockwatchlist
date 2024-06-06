package com.nashss.se.stockwatchlist.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import com.nashss.se.stockwatchlist.dynamodb.models.WatchList;
import com.nashss.se.stockwatchlist.execptions.WatchlistIsNotFoundException;
import com.nashss.se.stockwatchlist.metrics.MetricsConstants;
import com.nashss.se.stockwatchlist.metrics.MetricsPublisher;

import javax.inject.Inject;

public class WatchListDao {

    private final DynamoDBMapper dynamoDBMapper;
    private final MetricsPublisher metricsPublisher;

    @Inject

    public WatchListDao(DynamoDBMapper dynamoDBMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.metricsPublisher = metricsPublisher;
    }

//    public WatchList getWatchListByEmail(String email) {
//        WatchList watchLists = this.dynamoDBMapper.query(WatchList.class, email);
//
//        if (watchList == null) {
//            metricsPublisher.addCount(MetricsConstants.GETWATCHLIST_WATCHLISTNOTFOUND_COUNT, 1);
//            throw new WatchlistIsNotFoundException("Could not find watchlist with email" + email);
//        }
//
//        metricsPublisher.addCount(MetricsConstants.GETWATCHLIST_WATCHLISTNOTFOUND_COUNT, 0);
//        return watchLists;
//    }

    public WatchList saveWatchList(WatchList watchList) {
        this.dynamoDBMapper.save(watchList);
        return watchList;
    }

    public WatchList getWatchlist(WatchList watchList) {
        return this.dynamoDBMapper.load(watchList);
    }

    public WatchList deleteWatchList(WatchList watchList) {
        this.dynamoDBMapper.delete(watchList);
        return watchList;
    }


    //TODO need more functions

}
