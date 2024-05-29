package com.nashss.se.stockwatchlist.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.*;

@DynamoDBTable(tableName = "watchlist")
public class WatchList {
    private String userEmail;
    private String watchlistName;
    private List<String> stockSymbols;

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
    public List<String> getStockSymbols() {
        if(null == stockSymbols) {
            return null;
        }

        return new ArrayList<>(stockSymbols);
    }

    public void setStockSymbols(List<String> stockSymbols) {
        if (null == stockSymbols) {
            this.stockSymbols = null;
        } else {
            this.stockSymbols = new ArrayList<>(stockSymbols);
        }
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
