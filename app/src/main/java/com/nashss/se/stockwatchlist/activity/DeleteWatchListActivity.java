package com.nashss.se.stockwatchlist.activity;


import com.nashss.se.stockwatchlist.dynamodb.WatchListDao;
import com.nashss.se.stockwatchlist.metrics.MetricsPublisher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class DeleteWatchListActivity {

    private final Logger log = LogManager.getLogger();
    private final WatchListDao watchListDao;

    private final MetricsPublisher metricsPublisher;

    @Inject






}
