package com.nashss.se.stockwatchlist.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.nashss.se.stockwatchlist.dynamodb.models.WatchList;
import com.nashss.se.stockwatchlist.metrics.MetricsPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class WatchListDaoTest {
    @Mock
    private DynamoDBMapper dynamoDBMapper;
    @Mock
    private MetricsPublisher metricsPublisher;

    private WatchListDao watchListDao;

    @BeforeEach
    public void setup() {
        initMocks(this);
        watchListDao = new WatchListDao(dynamoDBMapper, metricsPublisher);
    }

    @Test
    public void saveWatchlist_callsMapperWithWatchlist() {
        //GIVEN
        WatchList watchList = new WatchList();

        //WHEN
        WatchList result = watchListDao.saveWatchList(watchList);

        //THEN
        verify(dynamoDBMapper).save(watchList);
        assertEquals(watchList,result);
    }

    @Test
    public void deleteWatchlist_callsMapperWithWatchlist() {
        //GIVEN
        WatchList watchList = new WatchList();

        //WHEN
        WatchList result = watchListDao.deleteWatchList(watchList);

        //THEN
        verify(dynamoDBMapper).delete(watchList);
        assertEquals(watchList, result);
    }

    @Test
    public void GetWatchListByEmail_watchlistIsNotEmpty() {
        //GIVEN
        String email = "test@example.com";
        WatchList watchList = new WatchList();
        PaginatedQueryList<WatchList> expectedWatchLists = mock(PaginatedQueryList.class);

        when(dynamoDBMapper.query(eq(WatchList.class), any(DynamoDBQueryExpression.class))).thenReturn(expectedWatchLists);

        //WHEN
        List<WatchList> actualWatchLists = watchListDao.getWatchListsByEmail(email);

        //THEN
        assertEquals(expectedWatchLists, actualWatchLists);
    }

    @Test
    public void ReadWatchListByEmail_watchlistIsEmpty() {
        //GIVEN
        String email = "test@example.com";
        PaginatedQueryList<WatchList> emptyWatchLists = mock(PaginatedQueryList.class);


        when(emptyWatchLists.isEmpty()).thenReturn(true);

        when(dynamoDBMapper.query(eq(WatchList.class), any(DynamoDBQueryExpression.class)))
                .thenReturn(emptyWatchLists);

        //WHEN
        List<WatchList> actualWatchLists = watchListDao.getWatchListsByEmail(email);

        //THEN
        assertNotNull(actualWatchLists);
        assertTrue(actualWatchLists.isEmpty());
    }

    @Test
    public void ReadWatchListByEmail_ThrowException () {
        //GIVEN
        String email = "test@example.com";
        String expectedMessage = "DynamoDB exception";

        when(dynamoDBMapper.query(eq(WatchList.class), any(DynamoDBQueryExpression.class)))
                .thenThrow(new RuntimeException("DynamoDB exception"));

        //WHEN
        watchListDao.getWatchListsByEmail(email);

        try {
            watchListDao.getWatchListsByEmail(email);
            fail("Expected RuntimeException to be thrown");
        } catch (RuntimeException e) {
            assertEquals(expectedMessage, e.getMessage());
        }

    }

}