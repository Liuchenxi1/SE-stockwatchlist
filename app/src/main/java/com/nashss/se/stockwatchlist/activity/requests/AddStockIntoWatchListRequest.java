package com.nashss.se.stockwatchlist.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = AddStockIntoWatchListRequest.Builder.class)
public class AddStockIntoWatchListRequest {

    private final String email;

    private final String stockSymbol;

    public AddStockIntoWatchListRequest(String email, String stockSymbol) {
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
        return "AddStockIntoWatchListRequest{" +
                "email='" + email + '\'' +
                ", stockSymbol='" + stockSymbol + '\'' +
                '}';
    }

    public static Builder builder() {return new Builder(); }

    @JsonPOJOBuilder
    public static class Builder{

        private String email;

        private String stockSymbol;

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withStockSymbol (String stockSymbol) {
            this.stockSymbol = stockSymbol;
            return this;
        }

        public AddStockIntoWatchListRequest build() {return new AddStockIntoWatchListRequest(email, stockSymbol); }

    }

}
