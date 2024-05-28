package com.nashss.se.stockwatchlist.activity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nashss.se.stockwatchlist.activity.requests.SearchStockInfoRequest;
import com.nashss.se.stockwatchlist.activity.results.SearchStockInfoResult;
import com.nashss.se.stockwatchlist.execptions.StockInfoNotFoundException;
import com.nashss.se.stockwatchlist.models.StockInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SearchStockInfoActivity {
    private final Logger logger = LogManager.getLogger();

    //No need WatchListDao;
    @Inject
    public SearchStockInfoActivity() {
    }

    public SearchStockInfoResult fetchStockInfo (SearchStockInfoRequest request) {
        String url = "https://query1.finance.yahoo.com/v8/finance/chart/" + request.getSymbol() + "?interval=1d&range=365d";

        try {
            URL yahooFinanceUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) yahooFinanceUrl.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            conn.disconnect();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(content.toString());

            JsonNode quoteNode = rootNode
                    .path("chart")
                    .path("result")
                    .get(0)
                    .path("indicators")
                    .path("quote")
                    .get(0);

            JsonNode timestampNode = rootNode.path("chart")
                    .path("result")
                    .get(0)
                    .path("timestamp");

            JsonNode volumeNode = quoteNode.path("volume");
            JsonNode lowNode = quoteNode.path("low");
            JsonNode closeNode = quoteNode.path("close");
            JsonNode openNode = quoteNode.path("open");
            JsonNode highNode = quoteNode.path("high");

            Integer[] volumeArray = objectMapper.treeToValue(volumeNode, Integer[].class);
            double[] lowArray = objectMapper.treeToValue(lowNode, double[].class);
            double[] closeArray = objectMapper.treeToValue(closeNode, double[].class);
            double[] openArray = objectMapper.treeToValue(openNode, double[].class);
            double[] highArray = objectMapper.treeToValue(highNode, double[].class);
            long[] timestampArray = objectMapper.treeToValue(timestampNode, long[].class);

            StockInfo stockInfo = StockInfo.builder()
                    .withTimestamps(timestampArray)
                    .withOpens(openArray)
                    .withCloses(closeArray)
                    .withLows(lowArray)
                    .withHighs(highArray)
                    .withVolumes(volumeArray)
                    .build();

            return SearchStockInfoResult.builder()
                    .withStockInfo(stockInfo)
                    .build();

        } catch (IOException e) //why this part can't use StockInfoNotFound?
        {
            logger.error("Error fetching stock information: ", e);
            return null;
        }
    }
}
