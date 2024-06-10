package com.nashss.se.stockwatchlist.models;

import java.util.Arrays;

public class StockInfo {
    private long[] timestamps;
    private double[] opens;
    private double[] closes;
    private double[] lows;
    private double[] highs;
    private Integer[] volumes;

    public StockInfo(long[] timestamps, double[] opens, double[] closes, double[] lows, double[] highs, Integer[] volumes) {
        this.timestamps = timestamps;
        this.opens = opens;
        this.closes = closes;
        this.lows = lows;
        this.highs = highs;
        this.volumes = volumes;
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

    public void setTimestamps(long[] timestamps) {
        this.timestamps = timestamps;
    }

    public void setOpens(double[] opens) {
        this.opens = opens;
    }

    public void setCloses(double[] closes) {
        this.closes = closes;
    }

    public void setLows(double[] lows) {
        this.lows = lows;
    }

    public void setHighs(double[] highs) {
        this.highs = highs;
    }

    public void setVolumes(Integer[] volumes) {
        this.volumes = volumes;
    }

    @Override
    public String toString() {
        return "StockInfo{" +
                "timestamps=" + Arrays.toString(timestamps) +
                ", opens=" + Arrays.toString(opens) +
                ", closes=" + Arrays.toString(closes) +
                ", lows=" + Arrays.toString(lows) +
                ", highs=" + Arrays.toString(highs) +
                ", volumes=" + Arrays.toString(volumes) +
                '}';
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private long[] timestamps;
        private double[] opens;
        private double[] closes;
        private double[] lows;
        private double[] highs;
        private Integer[] volumes;

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

        public StockInfo build() {
            return new StockInfo(timestamps, opens, closes, lows, highs, volumes);
        }

    }
}