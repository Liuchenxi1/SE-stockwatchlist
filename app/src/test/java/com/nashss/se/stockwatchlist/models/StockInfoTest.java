package com.nashss.se.stockwatchlist.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StockInfoTest {

    @Test
    public void testStockInfoBuilder() {
        // GIVEN
        String timestamp = "2023-06-17T10:00:00Z";
        double open = 100.5;
        double close = 105.3;
        double low = 99.8;
        double high = 106.0;
        int volume = 5000;

        // WHEN
        StockInfo stockInfo = StockInfo.builder()
                .withTimestamp(timestamp)
                .withOpen(open)
                .withClose(close)
                .withLow(low)
                .withHigh(high)
                .withVolume(volume)
                .build();

        // THEN
        assertEquals(timestamp, stockInfo.getTimestamp());
        assertEquals(open, stockInfo.getOpen());
        assertEquals(close, stockInfo.getClose());
        assertEquals(low, stockInfo.getLow());
        assertEquals(high, stockInfo.getHigh());
        assertEquals(volume, stockInfo.getVolume());
    }

    @Test
    public void testStockInfoRead() {
        // GIVEN
        String timestamp = "2023-06-17T10:00:00Z";
        double open = 100.5;
        double close = 105.3;
        double low = 99.8;
        double high = 106.0;
        int volume = 5000;

        StockInfo stockInfo = StockInfo.builder()
                .withTimestamp(timestamp)
                .withOpen(open)
                .withClose(close)
                .withLow(low)
                .withHigh(high)
                .withVolume(volume)
                .build();

        String expectedToString = "StockInfo{" +
                "timestamp=" + timestamp +
                ", open=" + open +
                ", close=" + close +
                ", low=" + low +
                ", high=" + high +
                ", volume=" + volume +
                '}';

        // WHEN
        String actualToString = stockInfo.toString();

        // THEN
        assertEquals(expectedToString, actualToString);
    }
}
