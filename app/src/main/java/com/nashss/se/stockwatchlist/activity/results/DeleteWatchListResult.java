package com.nashss.se.stockwatchlist.activity.results;


import com.nashss.se.stockwatchlist.models.WatchListModel;

public class DeleteWatchListResult {

    private final WatchListModel watchList;

    public DeleteWatchListResult(WatchListModel watchList) {
        this.watchList = watchList;
    }

    public WatchListModel getWatchList() {
        return watchList;
    }

    @Override
    public String toString() {
        return "DeleteWatchListResult{" +
                "watchList=" + watchList +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private WatchListModel watchList;

        public Builder witWatchListDelete (WatchListModel watchList) {
            this.watchList = watchList;
            return  this;
        }

        public DeleteWatchListResult build() {
            return new DeleteWatchListResult(watchList);
        }
    }

}
