package com.nashss.se.stockwatchlist.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = RemoveStockFromWatchListRequest.Builder.class)
public class RemoveStockFromWatchListRequest {

    private final String email;

    private final String watchlistName;

    private final String stockSymbol;

    public RemoveStockFromWatchListRequest(String email, String watchlistName, String stockSymbol) {
        this.email = email;
        this.watchlistName = watchlistName;
        this.stockSymbol = stockSymbol;
    }

    public String getEmail() {
        return email;
    }

    public String getWatchlistName() {
        return watchlistName;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    @Override
    public String toString() {
        return "RemoveStockFromWatchListRequest{" +
                "email='" + email + '\'' +
                ", watchlistName='" + watchlistName + '\'' +
                ", stockSymbol='" + stockSymbol + '\'' +
                '}';
    }

    public static RemoveStockFromWatchListRequest.Builder builder() {return new RemoveStockFromWatchListRequest.Builder(); }

    @JsonPOJOBuilder
    public static class Builder{

        private String email;

        private String watchlistName;

        private String stockSymbol;

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withWatchlistName (String watchlistName) {
            this.watchlistName = watchlistName;
            return this;
        }

        public Builder withStockSymbol (String stockSymbol) {
            this.stockSymbol = stockSymbol;
            return this;
        }

        public RemoveStockFromWatchListRequest build() {return new RemoveStockFromWatchListRequest(email, watchlistName, stockSymbol); }

    }
}
