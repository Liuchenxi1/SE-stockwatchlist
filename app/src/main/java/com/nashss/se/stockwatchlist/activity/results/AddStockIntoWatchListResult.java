package com.nashss.se.stockwatchlist.activity.results;

import java.util.ArrayList;
import java.util.List;

public class AddStockIntoWatchListResult {

    private final List<String> stockSymbols;

    public AddStockIntoWatchListResult(List<String> stockSymbols) {
        this.stockSymbols = new ArrayList<>(stockSymbols);
    }

    public List<String> getStockSymbols() {
        return new ArrayList<>(stockSymbols);
    }

    @Override
    public String toString() {
        return "AddStockIntoWatchListResult{" +
                "stockSymbols=" + stockSymbols +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<String> stockSymbols = new ArrayList<>();

        public Builder withStockSymbols (List<String> stockSymbols) {
            this.stockSymbols = new ArrayList<>(stockSymbols);
            return this;
        }

        public AddStockIntoWatchListResult build() {
            return new AddStockIntoWatchListResult(stockSymbols);
        }
    }
}
