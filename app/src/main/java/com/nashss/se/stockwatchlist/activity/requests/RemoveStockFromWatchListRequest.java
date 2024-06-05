package com.nashss.se.stockwatchlist.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = RemoveStockFromWatchListRequest.Builder.class)
public class RemoveStockFromWatchListRequest {

    private final String email;

    private final String stockSymbol;

    public RemoveStockFromWatchListRequest(String email, String stockSymbol) {
        this.email = email;
        this.stockSymbol = stockSymbol;
    }

    public String getEmail() {
        return email;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    @Override
    public String toString() {
        return "RemoveStockFromWatchListRequest{" +
                "email='" + email + '\'' +
                ", stockSymbol='" + stockSymbol + '\'' +
                '}';
    }

    public static RemoveStockFromWatchListRequest.Builder builder() {return new RemoveStockFromWatchListRequest.Builder(); }

    @JsonPOJOBuilder
    public static class Builder{

        private String email;

        private String stockSymbol;

        public RemoveStockFromWatchListRequest.Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public RemoveStockFromWatchListRequest.Builder withStockSymbol (String stockSymbol) {
            this.stockSymbol = stockSymbol;
            return this;
        }

        public RemoveStockFromWatchListRequest build() {return new RemoveStockFromWatchListRequest(email, stockSymbol); }

    }
}
