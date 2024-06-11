package com.nashss.se.stockwatchlist.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.nashss.se.stockwatchlist.models.StockInfo;

import java.util.List;

@JsonDeserialize(builder = SearchStockInfoRequest.Builder.class)
public class SearchStockInfoRequest {
    private final String symbol;
    private final List<StockInfo> stockInfoList;

    public SearchStockInfoRequest(String symbol, List<StockInfo> stockInfoList) {
        this.symbol = symbol;
        this.stockInfoList = stockInfoList;
    }

    private SearchStockInfoRequest(Builder builder) {
        this.symbol = builder.symbol;
        this.stockInfoList = builder.stockInfoList;
    }

    public String getSymbol() {
        return symbol;
    }

    public List<StockInfo> getStockInfoList() {
        return stockInfoList;
    }

    @Override
    public String toString() {
        return "SearchStockInfoRequest{" +
                "symbol='" + symbol + '\'' +
                ", stockInfoList=" + stockInfoList +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String symbol;
        private List<StockInfo> stockInfoList;

        public Builder withSymbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public Builder withStockInfoList(List<StockInfo> stockInfoList) {
            this.stockInfoList = stockInfoList;
            return this;
        }

        public SearchStockInfoRequest build() {
            return new SearchStockInfoRequest(symbol, stockInfoList);
        }
    }
}