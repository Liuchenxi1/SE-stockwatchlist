package com.nashss.se.stockwatchlist.activity;

import com.nashss.se.stockwatchlist.activity.requests.RemoveStockFromWatchListRequest;
import com.nashss.se.stockwatchlist.activity.results.RemoveStockFromWatchListResult;
import com.nashss.se.stockwatchlist.dynamodb.WatchListDao;
import com.nashss.se.stockwatchlist.dynamodb.models.WatchList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class RemoveStockFromWatchListActivityTest {

    @Mock
    private WatchListDao watchListDao;

    private RemoveStockFromWatchListActivity removeStockFromWatchListActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        removeStockFromWatchListActivity = new RemoveStockFromWatchListActivity(watchListDao);
    }

    String stock = "MSFT";
    String email = "test@example.com";
    String watchListName = "Tech";
    List<String> stockSymbols = Arrays.asList("MSFT","AAPL","GOOGL");

    @Test
    public void giveStock_removeFromList_handleSuccess() {
        //GIVEN
        WatchList watchList = new WatchList();
        watchList.setUserEmail(email);
        watchList.setWatchlistName(watchListName);
        watchList.setStockSymbols(stockSymbols);

        RemoveStockFromWatchListRequest request = RemoveStockFromWatchListRequest.builder()
                .withEmail(email)
                .withWatchlistName(watchListName)
                .withStockSymbol(stock)
                .build();

        WatchList expectedWatchList = new WatchList();
        expectedWatchList.setUserEmail(email);
        expectedWatchList.setWatchlistName(watchListName);
        expectedWatchList.setStockSymbols(Arrays.asList("AAPL", "GOOGL"));

        when(watchListDao.getWatchlist(any(WatchList.class))).thenReturn(watchList);
        when(watchListDao.saveWatchList(any(WatchList.class))).thenReturn(expectedWatchList);

        //WHEN
        RemoveStockFromWatchListResult result = removeStockFromWatchListActivity.handleRequest(request);

        //THEN
        assertNotNull(result);
        assertNotNull(result.getWatchList());
        assertFalse(result.getWatchList().getStockSymbols().contains(stock));
        assertTrue(result.getWatchList().getStockSymbols().size() == 2);
        verify(watchListDao).getWatchlist(any(WatchList.class));
        verify(watchListDao).saveWatchList(any(WatchList.class));

    }

}