package com.nashss.se.stockwatchlist.activity.requests;

import com.nashss.se.stockwatchlist.activity.results.SearchStockInfoResult;
import com.nashss.se.stockwatchlist.models.StockInfo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SearchStockInfoRequestTest {

    @Test
    void testBuilderPattern() {
        String symbol = "AAPL";
        long[] timestamps = {1627804800, 1627891200};
        double[] opens = {145.30, 146.00};
        double[] closes = {146.95, 147.36};
        double[] lows = {144.89, 145.63};
        double[] highs = {147.10, 148.19};
        Integer[] volumes = {1000, 1500};

        SearchStockInfoRequest request = SearchStockInfoRequest.builder()
                .withSymbol(symbol)
                .withTimestamps(timestamps)
                .withOpens(opens)
                .withCloses(closes)
                .withLows(lows)
                .withHighs(highs)
                .withVolumes(volumes)
                .build();

        assertEquals(symbol, request.getSymbol());
        assertArrayEquals(timestamps, request.getTimestamps());
        assertArrayEquals(opens, request.getOpens());
        assertArrayEquals(closes, request.getCloses());
        assertArrayEquals(lows, request.getLows());
        assertArrayEquals(highs, request.getHighs());
        assertArrayEquals(volumes, request.getVolumes());

        System.out.println(request);
    }
}
