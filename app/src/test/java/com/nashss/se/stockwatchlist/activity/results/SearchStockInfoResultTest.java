package com.nashss.se.stockwatchlist.activity.results;

import com.nashss.se.stockwatchlist.models.StockInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchStockInfoResultTest {
    @Test
    void testBuilderPattern() {

        long[] timestamps = {1627804800, 1627891200};
        double[] opens = {145.30, 146.00};
        double[] closes = {146.95, 147.36};
        double[] lows = {144.89, 145.63};
        double[] highs = {147.10, 148.19};
        Integer[] volumes = {1000, 1500};
        
        StockInfo stockInfo = new StockInfo(timestamps,opens,closes,lows,highs,volumes);

        SearchStockInfoResult result = SearchStockInfoResult.builder()
                .withStockInfo(stockInfo)
                .build();

        assertEquals(stockInfo, result.getStockInfo());
        System.out.println(result);
    }

}