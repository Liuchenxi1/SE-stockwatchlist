package com.nashss.se.stockwatchlist.activity.results;

import com.nashss.se.stockwatchlist.dynamodb.models.WatchList;
import com.nashss.se.stockwatchlist.models.WatchListModel;

import java.util.ArrayList;
import java.util.List;

public class AddStockIntoWatchListResult {

    private final WatchListModel watchList;

    public AddStockIntoWatchListResult(WatchListModel watchList) {
        this.watchList = watchList;
    }

    public WatchListModel getWatchList() {
        return watchList;
    }

    @Override
    public String toString() {
        return "AddStockIntoWatchListResult{" +
                "watchList=" + watchList +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private WatchListModel watchList;

        public Builder withWatchListModel (WatchListModel watchList) {
            this.watchList = watchList;
            return this;
        }

        public AddStockIntoWatchListResult build() {
            return new AddStockIntoWatchListResult(watchList);
        }
    }
}
