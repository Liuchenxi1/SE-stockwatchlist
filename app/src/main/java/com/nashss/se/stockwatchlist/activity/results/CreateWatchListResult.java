package com.nashss.se.stockwatchlist.activity.results;

import com.nashss.se.stockwatchlist.activity.requests.CreateWatchListRequest;
import com.nashss.se.stockwatchlist.models.watchListModel;

public class CreateWatchListResult {
    private final watchListModel watchList;

    private CreateWatchListResult (watchListModel watchList) {
        this.watchList = watchList;
    }

    public watchListModel getWatchList() {
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
        private watchListModel watchList;

        public Builder withWatchListModel (watchListModel watchList) {
            this.watchList = watchList;
            return this;
        }

        public CreateWatchListResult build() {
            return new CreateWatchListResult(watchList);
        }
    }
}
