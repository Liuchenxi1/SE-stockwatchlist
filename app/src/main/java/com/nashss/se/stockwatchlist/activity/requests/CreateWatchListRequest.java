package com.nashss.se.stockwatchlist.activity.requests;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;
import java.util.Locale;

@JsonDeserialize(builder = CreateWatchListRequest.Builder.class)
public class CreateWatchListRequest {

    private final String email;
    private final String watchListName;
    private final List<String> stockSymbols;


    public CreateWatchListRequest(String email, String watchListName, List<String> stockSymbols) {
        this.email = email;
        this.watchListName = watchListName;
        this.stockSymbols = stockSymbols;
    }

    public String getEmail() {
        return email;
    }

    public String getWatchListName() {
        return watchListName;
    }

    public List<String> getStockSymbols() {
        return stockSymbols;
    }

    @Override
    public String toString() {
        return "CreateWatchListRequest{" +
                "email='" + email + '\'' +
                ", watchListName='" + watchListName + '\'' +
                ", stockSymbols=" + stockSymbols +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder

    public static class Builder {
        private String email;
        private String watchListName;
        private List<String> stockSymbols;

        public Builder withEmail (String email) {
            this.email = email;
            return this;
        }

        public Builder withWatchListName (String watchListName) {
            this.watchListName = watchListName;
            return this;
        }

        public Builder withStockSymbols (List<String> stockSymbols) {
            this.stockSymbols = stockSymbols;
            return this;
        }

        public CreateWatchListRequest build() {
            return new CreateWatchListRequest(email,watchListName,stockSymbols);
        }

    }

}
