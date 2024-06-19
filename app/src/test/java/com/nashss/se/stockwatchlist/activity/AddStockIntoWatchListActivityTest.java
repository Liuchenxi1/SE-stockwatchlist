package com.nashss.se.stockwatchlist.activity;

import com.nashss.se.stockwatchlist.activity.requests.AddStockIntoWatchListRequest;
import com.nashss.se.stockwatchlist.activity.results.AddStockIntoWatchListResult;
import com.nashss.se.stockwatchlist.dynamodb.WatchListDao;
import com.nashss.se.stockwatchlist.dynamodb.models.WatchList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class AddStockIntoWatchListActivityTest {

    @Mock
    private WatchListDao watchListDao;

    private AddStockIntoWatchListActivity addStockIntoWatchListActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        addStockIntoWatchListActivity = new AddStockIntoWatchListActivity(watchListDao);
    }

    String stock = "MSFT";
    String email = "test@example.com";
    String watchListName = "Tech";
    List<String> stockSymbols = new ArrayList<>();

    @Test
    public void giveStock_addIntoList_handleSuccess() {
        // GIVEN
        WatchList watchList = new WatchList();
        watchList.setUserEmail(email);
        watchList.setWatchlistName(watchListName);
        watchList.setStockSymbols(stockSymbols);

        AddStockIntoWatchListRequest request = AddStockIntoWatchListRequest.builder()
                .withEmail(email)
                .withWatchlistName(watchListName)
                .withStockSymbol(stock)
                .build();

        when(watchListDao.getWatchlist(any(WatchList.class))).thenReturn(watchList);
        when(watchListDao.saveWatchList(any(WatchList.class))).thenReturn(watchList);

        // WHEN
        AddStockIntoWatchListResult result = addStockIntoWatchListActivity.handleRequest(request);

        // THEN
        assertNotNull(result);
        assertNotNull(result.getWatchList());
        verify(watchListDao).getWatchlist(any(WatchList.class));
        verify(watchListDao).saveWatchList(any(WatchList.class));
    }

}