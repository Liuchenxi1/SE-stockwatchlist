package com.nashss.se.stockwatchlist.activity;

import com.nashss.se.stockwatchlist.activity.requests.SearchStockInfoRequest;
import com.nashss.se.stockwatchlist.activity.results.SearchStockInfoResult;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchStockInfoActivityTest {

    @Test
    void testFetchStockInfo() throws Exception {
        HttpURLConnection mockConnection = mock(HttpURLConnection.class);
        when(mockConnection.getInputStream()).thenReturn(new ByteArrayInputStream(
                "{ \"chart\": { \"result\": [ { \"indicators\": { \"quote\": [ { \"volume\": [1000], \"low\": [144.89], \"close\": [146.95], \"open\": [145.30], \"high\": [147.10] } ] }, \"timestamp\": [1627804800] } ] } }".getBytes()));

        URL mockUrl = mock(URL.class);
        when(mockUrl.openConnection()).thenReturn(mockConnection);

        SearchStockInfoActivity activity = Mockito.spy(new SearchStockInfoActivity());
        doReturn(mockUrl).when(activity).createUrl(anyString());

        SearchStockInfoRequest request = SearchStockInfoRequest.builder()
                .withSymbol("AAPL")
                .build();

        // Execute
        SearchStockInfoResult result = activity.fetchStockInfo(request);

        assertNotNull(result);
        assertNotNull(result.getStockInfo());
        assertArrayEquals(new long[]{1627804800}, result.getStockInfo().getTimestamps());
    }
}
