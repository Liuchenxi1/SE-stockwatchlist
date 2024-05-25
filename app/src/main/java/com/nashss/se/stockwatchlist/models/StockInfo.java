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
}
