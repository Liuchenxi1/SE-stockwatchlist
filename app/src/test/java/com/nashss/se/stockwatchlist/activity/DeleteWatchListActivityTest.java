package com.nashss.se.stockwatchlist.activity;

import com.nashss.se.stockwatchlist.activity.requests.DeleteWatchListRequest;
import com.nashss.se.stockwatchlist.activity.results.DeleteWatchListResult;
import com.nashss.se.stockwatchlist.dynamodb.WatchListDao;
import com.nashss.se.stockwatchlist.dynamodb.models.WatchList;
import com.nashss.se.stockwatchlist.execptions.WatchlistIsNotFoundException;
import com.nashss.se.stockwatchlist.metrics.MetricsPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DeleteWatchListActivityTest {

    @Mock
    private WatchListDao watchListDao;

    @Mock
    private MetricsPublisher metricsPublisher;

    @InjectMocks
    private DeleteWatchListActivity deleteWatchListActivity;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHandleRequest_ValidRequest() {
        //GIVEN
        DeleteWatchListRequest request = new DeleteWatchListRequest("test@email.com", "WatchlistName");

        WatchList watchListToDelete = new WatchList();
        watchListToDelete.setUserEmail(request.getEmail());
        watchListToDelete.setWatchlistName(request.getWatchlistName());
        when(watchListDao.deleteWatchList(any(WatchList.class))).thenReturn(watchListToDelete);

        //WHEN
        DeleteWatchListResult result = deleteWatchListActivity.handleRequest(request);

        //THEN
        assertNotNull(result);
        assertNotNull(result.getWatchList());
        assertEquals(request.getWatchlistName(), result.getWatchList().getWatchlistName());

    }

    @Test
    public void testHandleRequest_InvalidRequest() {
        //GIVEN
        DeleteWatchListRequest request = new DeleteWatchListRequest("", "");

        //THEN
        assertThrows(IllegalArgumentException.class, () -> deleteWatchListActivity.handleRequest(request));

    }

    @Test
    public void testHandleRequest_WatchlistNotFound() {
        // Prepare test data
        DeleteWatchListRequest request = new DeleteWatchListRequest("test@email.com", "NonExistingWatchlist");

        // Mock behavior of watchListDao
        when(watchListDao.deleteWatchList(any(WatchList.class))).thenReturn(null);

        // Perform the action and catch the exception
        assertThrows(WatchlistIsNotFoundException.class, () -> deleteWatchListActivity.handleRequest(request));
    }
}
