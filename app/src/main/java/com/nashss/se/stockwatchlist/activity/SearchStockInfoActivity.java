package com.nashss.se.stockwatchlist.activity;

import com.nashss.se.stockwatchlist.activity.results.SearchStockInfoResult;
import com.nashss.se.stockwatchlist.models.StockInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class SearchStockInfoActivity {
    private final Logger logger = LogManager.getLogger();
    private final StockInfo stockInfo;

    //No neeed WatchListDao;

    @Inject
    public SearchStockInfoActivity(StockInfo stockInfo) {
        this.stockInfo = stockInfo;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {





    }

}
