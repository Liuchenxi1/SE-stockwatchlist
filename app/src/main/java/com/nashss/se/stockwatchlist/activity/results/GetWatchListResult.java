package com.nashss.se.stockwatchlist.activity.results;

import com.nashss.se.stockwatchlist.models.WatchListModel;

public class GetWatchListResult {

    private final WatchListModel watchList;

    public GetWatchListResult (WatchListModel watchList) {
        this.watchList = watchList;
    }

    public WatchListModel getWatchList() {
        return watchList;
    }

    @Override
    public String toString() {
        return "GetWatchListResult{" +
                "watchList=" + watchList +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private WatchListModel watchList;

        public Builder withGetWatchList (WatchListModel watchList) {
            this.watchList = watchList;
            return this;
        }

        public GetWatchListResult build() { return new GetWatchListResult(watchList); }

    }

}
