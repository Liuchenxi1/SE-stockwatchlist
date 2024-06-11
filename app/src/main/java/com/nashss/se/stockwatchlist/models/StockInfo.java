package com.nashss.se.stockwatchlist.models;

public class StockInfo {

    private final String timestamp;
    private final double open;
    private final double close;
    private final double low;
    private final double high;
    private final int volume;

    private StockInfo(String timestamp, double open, double close, double low, double high, int volume) {
        this.timestamp = timestamp;
        this.open = open;
        this.close = close;
        this.low = low;
        this.high = high;
        this.volume = volume;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public double getOpen() {
        return open;
    }

    public double getClose() {
        return close;
    }

    public double getLow() {
        return low;
    }

    public double getHigh() {
        return high;
    }

    public int getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return "StockInfo{" +
                "timestamp=" + timestamp +
                ", open=" + open +
                ", close=" + close +
                ", low=" + low +
                ", high=" + high +
                ", volume=" + volume +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String timestamp;
        private double open;
        private double close;
        private double low;
        private double high;
        private int volume;

        public Builder withTimestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder withOpen(double open) {
            this.open = open;
            return this;
        }

        public Builder withClose(double close) {
            this.close = close;
            return this;
        }

        public Builder withLow(double low) {
            this.low = low;
            return this;
        }

        public Builder withHigh(double high) {
            this.high = high;
            return this;
        }

        public Builder withVolume(int volume) {
            this.volume = volume;
            return this;
        }

        public StockInfo build() {
            return new StockInfo(timestamp, open, close, low, high, volume);
        }
    }
}


