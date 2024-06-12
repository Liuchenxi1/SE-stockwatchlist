package com.nashss.se.stockwatchlist.activity.results;

import com.nashss.se.stockwatchlist.models.WatchListModel;

import java.util.List;

public class GetWatchListsResult {

    private final List<WatchListModel> watchLists;

    public GetWatchListsResult(List<WatchListModel> watchLists) {
        this.watchLists = watchLists;
    }

    public List<WatchListModel> getWatchLists() {
        return watchLists;
    }

    @Override
    public String toString() {
        return "GetWatchListsResult{" +
                "watchLists=" + watchLists +
                '}';
    }

    public static Builder builder() {return new Builder();
    }

    public static class Builder {

        private List<WatchListModel> watchLists;

        public Builder withWatchLists(List<WatchListModel> watchLists) {
            this.watchLists = watchLists;
            return this;
        }

        public GetWatchListsResult build() {return new GetWatchListsResult(watchLists);}
    }

}
