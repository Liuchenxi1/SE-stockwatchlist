package com.nashss.se.stockwatchlist.activity;


import com.nashss.se.stockwatchlist.activity.requests.DeleteWatchListRequest;
import com.nashss.se.stockwatchlist.activity.results.DeleteWatchListResult;
import com.nashss.se.stockwatchlist.converters.ModelConverter;
import com.nashss.se.stockwatchlist.dynamodb.WatchListDao;
import com.nashss.se.stockwatchlist.dynamodb.models.WatchList;
import com.nashss.se.stockwatchlist.metrics.MetricsConstants;
import com.nashss.se.stockwatchlist.metrics.MetricsPublisher;
import com.nashss.se.stockwatchlist.models.WatchListModel;
import com.nashss.se.stockwatchlist.utils.watchlistServiceUtils;
import com.nashss.se.stockwatchlist.execptions.InvalidAttributeValueException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;


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
        log.info("Received UpdatePlaylistRequest {}", deleteWatchListRequest);

        if(!watchlistServiceUtils.isValidString(deleteWatchListRequest.getWatchlistName())) {
            publishExceptionMetrics(true, false);
            throw new InvalidAttributeValueException("Watchlist name [" + deleteWatchListRequest.getWatchlistName() +
                "] contains illegal characters");
        }

        WatchList watchList = watchListDao.getWatchList(deleteWatchListRequest.getWatchlistName());

        if(!watchList.getWatchlistName().equals(deleteWatchListRequest.getWatchlistName())){
            publishExceptionMetrics(false, true);
            throw new SecurityException("You don't have a watch list to delete");
        }

        WatchList result = watchListDao.deleteWatchList(watchList);

        WatchListModel watchListToDelete = new ModelConverter().deleteWatchlistModel(result);
        //I think ModelCoverter.java is correct!!!

        return DeleteWatchListResult.builder()
                .witWatchListDelete(watchListToDelete)
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
