package com.nashss.se.stockwatchlist.activity;

import com.nashss.se.stockwatchlist.activity.requests.GetWatchListsRequest;
import com.nashss.se.stockwatchlist.activity.results.GetWatchListsResult;
import com.nashss.se.stockwatchlist.dynamodb.WatchListDao;
import com.nashss.se.stockwatchlist.dynamodb.models.WatchList;
import com.nashss.se.stockwatchlist.models.WatchListModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


class GetWatchListsActivityTest {

    @Mock
    private WatchListDao watchListDao;

    private GetWatchListsActivity getWatchListsActivity;

    @BeforeEach
    public void setup() {
        initMocks(this);
        getWatchListsActivity = new GetWatchListsActivity(watchListDao);
    }

    @Test
    public void handleRequest_emptyEmail_returnsEmptyList() {
        //GIVEN
        GetWatchListsRequest request = GetWatchListsRequest.builder().withEmail(null).build();

        Exception exception = assertThrows(RuntimeException.class, ()-> {
            getWatchListsActivity.handleRequest(request);
        });
        assertEquals("Email is null in GetWatchListsRequest", exception.getMessage());
    }

    @Test
    public void handleRequest_emptyWatchlist_returnsEmptyList() {
        //GIVEN
        String email = "test@example.com";
        GetWatchListsRequest request = GetWatchListsRequest.builder().withEmail(email).build();

        when(watchListDao.getWatchListsByEmail(email)).thenReturn(new ArrayList<>());

        //WHEN
        GetWatchListsResult result = getWatchListsActivity.handleRequest(request);

        //THEN
        assertNotNull(result);
        assertNotNull(result.getWatchLists());
        assertTrue(result.getWatchLists().isEmpty());
    }

    @Test
    public void handleRequest_nonEmptyWatchlist_returnsWatchlist() {
        // GIVEN
        String email = "test@example.com";
        String watchlistName = "Tech Stocks";
        List<String> stockSymbols = Arrays.asList("AAPL", "MSFT", "GOOGL");
        GetWatchListsRequest request = GetWatchListsRequest.builder().withEmail(email).build();

        WatchList watchList = new WatchList();
        watchList.setWatchlistName(watchlistName);
        watchList.setStockSymbols(stockSymbols);

        List<WatchList> watchLists = new ArrayList<>();
        watchLists.add(watchList);

        when(watchListDao.getWatchListsByEmail(email)).thenReturn(watchLists);

        // WHEN
        GetWatchListsResult result = getWatchListsActivity.handleRequest(request);

        // THEN
        assertNotNull(result);
        assertNotNull(result.getWatchLists());
        assertFalse(result.getWatchLists().isEmpty());
        assertEquals(1, result.getWatchLists().size());
        assertEquals("Tech Stocks", result.getWatchLists().get(0).getWatchlistName());
        WatchListModel watchListModel = result.getWatchLists().get(0);
        assertEquals("Tech Stocks", watchListModel.getWatchlistName());
        }

}