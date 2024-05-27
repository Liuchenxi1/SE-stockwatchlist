package com.nashss.se.stockwatchlist.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Arrays;

@JsonDeserialize(builder = SearchStockInfoRequest.Builder.class)
public class SearchStockInfoRequest {
    private final String symbol;
    private final long[] timestamps;
    private final double[] opens;
    private final double[] closes;
    private final double[] lows;
    private final double[] highs;
    private final Integer[] volumes;

    private SearchStockInfoRequest(Builder builder) {
        this.symbol = builder.symbol;
        this.timestamps = builder.timestamps;
        this.opens = builder.opens;
        this.closes = builder.closes;
        this.lows = builder.lows;
        this.highs = builder.highs;
        this.volumes = builder.volumes;
    }

    public String getSymbol() {
        return symbol;
    }

    public long[] getTimestamps() {
        return timestamps;
    }

    public double[] getOpens() {
        return opens;
    }

    public double[] getCloses() {
        return closes;
    }

    public double[] getLows() {
        return lows;
    }

    public double[] getHighs() {
        return highs;
    }

    public Integer[] getVolumes() {
        return volumes;
    }

    @Override
    public String toString() {
        return "SearchStockInfoRequest{" +
                "symbol='" + symbol + '\'' +
                ", timestamps=" + Arrays.toString(timestamps) +
                ", opens=" + Arrays.toString(opens) +
                ", closes=" + Arrays.toString(closes) +
                ", lows=" + Arrays.toString(lows) +
                ", highs=" + Arrays.toString(highs) +
                ", volumes=" + Arrays.toString(volumes) +
                '}';
    }

    public static Builder builder() { return new Builder(); }

    @JsonPOJOBuilder
    public static class Builder {
        private String symbol;
        private long[] timestamps;
        private double[] opens;
        private double[] closes;
        private double[] lows;
        private double[] highs;
        private Integer[] volumes;

        public Builder withSymbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public Builder withTimestamps(long[] timestamps) {
            this.timestamps = timestamps;
            return this;
        }

        public Builder withOpens(double[] opens) {
            this.opens = opens;
            return this;
        }

        public Builder withCloses(double[] closes) {
            this.closes = closes;
            return this;
        }

        public Builder withLows(double[] lows) {
            this.lows = lows;
            return this;
        }

        public Builder withHighs(double[] highs) {
            this.highs = highs;
            return this;
        }

        public Builder withVolumes(Integer[] volumes) {
            this.volumes = volumes;
            return this;
        }

        public SearchStockInfoRequest build() {
            return new SearchStockInfoRequest(this);
        }
    }
}

