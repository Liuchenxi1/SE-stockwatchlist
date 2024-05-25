package com.nashss.se.stockwatchlist.activity.results;

import com.nashss.se.stockwatchlist.models.WatchListModel;

public class CreateWatchListResult {
    private final WatchListModel watchList;

    private CreateWatchListResult (WatchListModel watchList) {
        this.watchList = watchList;
    }

    public WatchListModel getWatchList() {
        return watchList;
    }

    @Override
    public String toString() {
        return "CreateWatchListResult{" +
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

        public CreateWatchListResult build() {
            return new CreateWatchListResult(watchList);
        }
    }
}
