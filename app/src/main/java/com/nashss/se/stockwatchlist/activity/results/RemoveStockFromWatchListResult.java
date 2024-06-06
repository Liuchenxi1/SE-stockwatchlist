package com.nashss.se.stockwatchlist.activity.results;

import com.nashss.se.stockwatchlist.models.WatchListModel;

public class RemoveStockFromWatchListResult {

    private final WatchListModel watchList;

    public RemoveStockFromWatchListResult(WatchListModel watchList) {
        this.watchList = watchList;
    }

    public WatchListModel getWatchList() {
        return watchList;
    }

    @Override
    public String toString() {
        return "RemoveStockFromWatchListResult{" +
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

        public RemoveStockFromWatchListResult build() {
            return new RemoveStockFromWatchListResult(watchList);
        }
    }
}
