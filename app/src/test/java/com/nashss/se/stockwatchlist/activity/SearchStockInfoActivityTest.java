package com.nashss.se.stockwatchlist.activity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nashss.se.stockwatchlist.activity.requests.SearchStockInfoRequest;
import com.nashss.se.stockwatchlist.activity.results.SearchStockInfoResult;
import com.nashss.se.stockwatchlist.models.StockInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchStockInfoActivityTest {

    @Test
    public void testFetchStockInfo() throws Exception {
        // Setup mock objects and input data
        SearchStockInfoRequest request = new SearchStockInfoRequest("AAPL");

        SearchStockInfoActivity activity = new SearchStockInfoActivity();
        SearchStockInfoResult result = activity.fetchStockInfo(request);

        assertNotNull(result);
        assertFalse(result.getStockInfoList().isEmpty());

        StockInfo firstStockInfo = result.getStockInfoList().get(0);
        assertNotNull(firstStockInfo.getTimestamp());
        assertTrue(firstStockInfo.getOpen() >= 0.00);
        assertTrue(firstStockInfo.getClose() >= 0.00);
        assertTrue(firstStockInfo.getLow() >= 0.00);
        assertTrue(firstStockInfo.getHigh() >= 0.00);
        assertTrue(firstStockInfo.getVolume() >= 0);
    }
}