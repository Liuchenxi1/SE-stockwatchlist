package com.nashss.se.stockwatchlist.activity;


import com.nashss.se.stockwatchlist.activity.requests.CreateWatchListRequest;
import com.nashss.se.stockwatchlist.activity.results.CreateWatchListResult;
import com.nashss.se.stockwatchlist.dynamodb.WatchListDao;
import com.nashss.se.stockwatchlist.dynamodb.models.WatchList;
import com.nashss.se.stockwatchlist.execptions.InvalidAttributeValueException;
import com.nashss.se.stockwatchlist.utils.watchlistServiceUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class CreateWatchListActivityTest {

    @Mock
    private WatchListDao watchListDao;

    private CreateWatchListActivity createWatchListActivity;

    @BeforeEach
    void setUp() {
        openMocks(this);
        createWatchListActivity = new CreateWatchListActivity(watchListDao);
    }

    @Test
    public void handleRequest_validRequest_createsWatchList() {
        String email = "test@example.com";
        String watchListName = "Tech";
        List<String> stocks = Arrays.asList("AAPL", "MSFT", "GOOG");

        CreateWatchListRequest request = CreateWatchListRequest.builder()
                .withEmail(email)
                .withWatchlistName(watchListName)
                .withStockSymbols(stocks)
                .build();

        WatchList expectedWatchList = new WatchList();
        expectedWatchList.setUserEmail(email);
        expectedWatchList.setWatchlistName(watchListName);
        expectedWatchList.setStockSymbols(stocks);

        when(watchListDao.saveWatchList(any(WatchList.class))).thenReturn(expectedWatchList);

        // WHEN
        CreateWatchListResult result = createWatchListActivity.handleRequest(request);

        // THEN
        verify(watchListDao).saveWatchList(any(WatchList.class));

        assertNotNull(result.getWatchList());
        assertEquals(email, result.getWatchList().getEmail());
        assertEquals(watchListName, result.getWatchList().getWatchlistName());
        assertEquals(stocks, result.getWatchList().getStockSymbols());
    }

    @Test
    public void handleRequest_NoEamil_createsWatchList() {
        // GIVEN

        CreateWatchListRequest request = CreateWatchListRequest.builder()
                .withEmail(null)
                .build();

        // WHEN & THEN
        assertThrows(InvalidAttributeValueException.class, () -> {
            createWatchListActivity.handleRequest(request);
        });
    }



}