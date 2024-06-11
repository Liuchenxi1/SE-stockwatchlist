package com.nashss.se.stockwatchlist.activity.results;

import com.nashss.se.stockwatchlist.models.StockInfo;

import java.util.List;


public class SearchStockInfoResult {

    private final List<StockInfo> stockInfoList;

    private SearchStockInfoResult(List<StockInfo> stockInfoList) {
        this.stockInfoList = stockInfoList;
    }

    public List<StockInfo> getStockInfoList() {
        return stockInfoList;
    }

    @Override
    public String toString() {
        return "SearchStockInfoResult{" +
                "stockInfoList=" + stockInfoList +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<StockInfo> stockInfoList;

        public Builder withStockInfoList(List<StockInfo> stockInfoList) {
            this.stockInfoList = stockInfoList;
            return this;
        }

        public SearchStockInfoResult build() {
            return new SearchStockInfoResult(stockInfoList);
        }
    }
}

