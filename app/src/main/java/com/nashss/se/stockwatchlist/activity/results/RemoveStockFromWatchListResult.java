package com.nashss.se.stockwatchlist.activity.results;

import java.util.ArrayList;
import java.util.List;

public class RemoveStockFromWatchListResult {

    private final List<String> stockSymbols;

    public RemoveStockFromWatchListResult(List<String> stockSymbols) {
        this.stockSymbols = stockSymbols;
    }

    public List<String> getStockSymbols() {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "RemoveStockFromWatchListResult{" +
                "stockSymbols=" + stockSymbols +
                '}';
    }

    public static RemoveStockFromWatchListResult.Builder builder() {
        return new RemoveStockFromWatchListResult.Builder();
    }

    public static class Builder {
        private List<String> stockSymbols;

        public RemoveStockFromWatchListResult.Builder withStockSymbols (List<String> stockSymbols) {
            this.stockSymbols = new ArrayList<>();
            return this;
        }

        public RemoveStockFromWatchListResult build() {
            return new RemoveStockFromWatchListResult(stockSymbols);
        }
    }
}
