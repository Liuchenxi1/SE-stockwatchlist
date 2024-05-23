package com.nashss.se.stockwatchlist.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@DynamoDBTable(tableName = "stockwatchlist")
public class WatchList {
    private String userEmail;
    private String watchlistName;
    private Set<String> stockSymbols;

    @DynamoDBHashKey(attributeName = "UserEmail")
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @DynamoDBRangeKey(attributeName = "WatchlistName")
    public String getWatchlistName() {
        return watchlistName;
    }

    public void setWatchlistName(String watchlistName) {
        this.watchlistName = watchlistName;
    }

    @DynamoDBAttribute(attributeName = "StockSymbols")
    public Set<String> getStockSymbols() {
        if(null == stockSymbols) {
            return null;
        }

        return new HashSet<>(stockSymbols);
    }

    public void setStockSymbols(Set<String> stockSymbols) {
        if (null == stockSymbols) {
            this.stockSymbols = null;
        } else {
            this.stockSymbols = new HashSet<>(stockSymbols);
        }

        this.stockSymbols = stockSymbols;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WatchList watchList = (WatchList) o;
        return Objects.equals(userEmail, watchList.userEmail) &&
                Objects.equals(watchlistName, watchList.watchlistName) &&
                Objects.equals(stockSymbols, watchList.stockSymbols);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userEmail, watchlistName, stockSymbols);
    }

}
