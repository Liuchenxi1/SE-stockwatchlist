package com.nashss.se.stockwatchlist.models;

import java.util.List;
import java.util.Objects;

public class watchListModel {
    private final String email;
    private final String watchlistName;
    private final List<String> stockSymbols;

    public watchListModel(String email, String watchlistName, List<String> stockSymbols) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof watchListModel)) return false;
        watchListModel that = (watchListModel) o;
        return Objects.equals(email, that.email) && Objects.equals(watchlistName, that.watchlistName) && Objects.equals(stockSymbols, that.stockSymbols);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, watchlistName, stockSymbols);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String email;
        private String watchlistName;
        private List<String> stockSymbols;

        public Builder withEmail(String email) {
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

        public watchListModel build() {
            return new watchListModel(email,watchlistName,stockSymbols);
        }
    }
}
