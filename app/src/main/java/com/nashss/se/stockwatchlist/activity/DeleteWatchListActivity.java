package com.nashss.se.stockwatchlist.activity;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.nashss.se.stockwatchlist.activity.requests.DeleteWatchListRequest;
import com.nashss.se.stockwatchlist.activity.results.DeleteWatchListResult;

import com.nashss.se.stockwatchlist.converters.ModelConverter;
import com.nashss.se.stockwatchlist.dynamodb.WatchListDao;
import com.nashss.se.stockwatchlist.dynamodb.models.WatchList;
import com.nashss.se.stockwatchlist.execptions.WatchlistIsNotFoundException;
import com.nashss.se.stockwatchlist.metrics.MetricsConstants;
import com.nashss.se.stockwatchlist.metrics.MetricsPublisher;
import com.nashss.se.stockwatchlist.models.WatchListModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

import static com.nashss.se.stockwatchlist.utils.watchlistServiceUtils.isValidString;


public class DeleteWatchListActivity {

    private final Logger log = LogManager.getLogger();
    private final WatchListDao watchListDao;
    private final MetricsPublisher metricsPublisher;

    @Inject
    public DeleteWatchListActivity(WatchListDao watchListDao, MetricsPublisher metricsPublisher) {
        this.watchListDao = watchListDao;
        this.metricsPublisher = metricsPublisher;
    }

    public DeleteWatchListResult handleRequest (final DeleteWatchListRequest deleteWatchListRequest) {
        log.info("Received UpdateWatchlistRequest {}", deleteWatchListRequest);

        if (!isValidString(deleteWatchListRequest.getWatchlistName())) {
            publishExceptionMetrics(true, false);
            throw new IllegalArgumentException("Invalid user email or watchlist name");
        }

        WatchList watchListToDelete = new WatchList();
        watchListToDelete.setUserEmail(deleteWatchListRequest.getEmail());
        watchListToDelete.setWatchlistName(deleteWatchListRequest.getWatchlistName());

        WatchList result = watchListDao.deleteWatchList(watchListToDelete);

        if(result == null) {
            throw new WatchlistIsNotFoundException("Watchlist not found");
        }

        WatchListModel watchListModel = new ModelConverter().deleteWatchlistModel(result);

        return DeleteWatchListResult.builder()
                .witWatchListDelete(watchListModel)
                .build();
    }

    private void publishExceptionMetrics(final boolean isInvalidAttributeValue,
                                         final boolean isInvalidAttributeChange) {
        metricsPublisher.addCount(MetricsConstants.UPDATEWATCHLIST_INVALIDATTRIBUTEVALUE_COUNT,
                isInvalidAttributeValue ? 1 : 0);
        metricsPublisher.addCount(MetricsConstants.UPDATEWATCHLIST_INVALIDATTRIBUTECHANGE_COUNT,
                isInvalidAttributeChange ? 1 : 0);
    }
}
