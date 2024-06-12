package com.nashss.se.stockwatchlist.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.nashss.se.stockwatchlist.models.StockInfo;

import java.util.List;

@JsonDeserialize(builder = SearchStockInfoRequest.Builder.class)
public class SearchStockInfoRequest {
    private final String symbol;

    public SearchStockInfoRequest(String symbol) {
        this.symbol = symbol;
    }

    private SearchStockInfoRequest(Builder builder) {
        this.symbol = builder.symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return "SearchStockInfoRequest{" +
                "symbol='" + symbol + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String symbol;

        public Builder withSymbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public SearchStockInfoRequest build() {
            return new SearchStockInfoRequest(symbol);
        }
    }
}