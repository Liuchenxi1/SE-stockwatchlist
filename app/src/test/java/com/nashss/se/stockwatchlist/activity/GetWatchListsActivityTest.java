package com.nashss.se.stockwatchlist.activity;

import com.nashss.se.stockwatchlist.activity.requests.GetWatchListsRequest;
import com.nashss.se.stockwatchlist.activity.results.GetWatchListsResult;
import com.nashss.se.stockwatchlist.dynamodb.WatchListDao;
import com.nashss.se.stockwatchlist.execptions.WatchlistIsNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

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



}