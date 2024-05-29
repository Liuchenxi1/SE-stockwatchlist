package com.nashss.se.stockwatchlist.activity.requests;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;

@JsonDeserialize(builder = CreateWatchListRequest.Builder.class)
public class CreateWatchListRequest {

    private final String email;
    private final String watchlistName;
    private final List<String> stockSymbols;


    public CreateWatchListRequest(String email, String watchlistName, List<String> stockSymbols) {
        this.email = email;
        this.watchlistName = watchlistName;
        this.stockSymbols = stockSymbols;
    }

    public String getEmail() {
        return email;
    }

    public String getWatchlistName() {
        return watchlistName;
    }

    public List<String> getStockSymbols() {
        return stockSymbols;
    }

    @Override
    public String toString() {
        return "CreateWatchListRequest{" +
                "email='" + email + '\'' +
                ", watchlistName='" + watchlistName + '\'' +
                ", stockSymbols=" + stockSymbols +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String email;
        private String watchlistName;
        private List<String> stockSymbols;

        public Builder withEmail (String email) {
            this.email = email;
            return this;
        }

        public Builder withWatchlistName (String watchlistName) {
            this.watchlistName = watchlistName;
            return this;
        }

        public Builder withStockSymbols (List<String> stockSymbols) {
            this.stockSymbols = stockSymbols;
            return this;
        }

        public CreateWatchListRequest build() {
            return new CreateWatchListRequest(email,watchlistName,stockSymbols);
        }

    }

}
