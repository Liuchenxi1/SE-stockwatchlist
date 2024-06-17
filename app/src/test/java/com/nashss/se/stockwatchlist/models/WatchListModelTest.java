package com.nashss.se.stockwatchlist.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class WatchListModelTest {

    @Test
    public void testEqualsAndHashCode() {
        // GIVEN
        String email1 = "user@example.com";
        String watchlistName1 = "Tech Stocks";
        List<String> stockSymbols1 = Arrays.asList("AAPL", "GOOGL", "MSFT");

        String email2 = "user@example.com";
        String watchlistName2 = "Tech Stocks";
        List<String> stockSymbols2 = Arrays.asList("AAPL", "GOOGL", "MSFT");

        WatchListModel watchListModel1 = new WatchListModel(email1, watchlistName1, stockSymbols1);
        WatchListModel watchListModel2 = new WatchListModel(email2, watchlistName2, stockSymbols2);

        // THEN
        assertEquals(watchListModel1, watchListModel2);
        assertEquals(watchListModel1.hashCode(), watchListModel2.hashCode());
    }

    @Test
    public void testNotEquals() {
        // GIVEN
        WatchListModel watchListModel1 = new WatchListModel("user1@example.com", "Tech Stocks", Arrays.asList("AAPL", "GOOGL", "MSFT"));
        WatchListModel watchListModel2 = new WatchListModel("user2@example.com", "Tech Stocks", Arrays.asList("AAPL", "GOOGL", "MSFT"));
        WatchListModel watchListModel3 = new WatchListModel("user1@example.com", "Tech Stocks", Arrays.asList("TSLA", "NFLX"));

        // THEN
        assertNotEquals(watchListModel1, watchListModel2);
        assertNotEquals(watchListModel1, watchListModel3);
    }
}
