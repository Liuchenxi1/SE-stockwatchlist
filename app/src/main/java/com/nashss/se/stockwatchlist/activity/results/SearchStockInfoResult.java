package com.nashss.se.stockwatchlist.activity.results;

import com.nashss.se.stockwatchlist.models.StockInfo;

public class
SearchStockInfoResult {

    private final StockInfo stockInfo;

    private SearchStockInfoResult (StockInfo stockInfo) {
        this.stockInfo = stockInfo;

    }

    public StockInfo getStockInfo() {
        return stockInfo;
    }

    @Override
    public String toString() {
        return "SearchStockInfoResult{" +
                "stockInfo=" + stockInfo +
                '}';
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private StockInfo stockInfo;

        public Builder withStockInfo (StockInfo stockInfo) {
            this.stockInfo = stockInfo;
            return this;
        }

        public SearchStockInfoResult build() { return new SearchStockInfoResult(stockInfo); }
    }
}
