package com.nashss.se.stockwatchlist.activity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.IntNode;
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
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SearchStockInfoActivity {
    private final Logger logger = LogManager.getLogger();

    //No need WatchListDao;
    @Inject
    public SearchStockInfoActivity() {
    }

    private JsonNode replaceNullWithDefault(JsonNode node, double defaultValue) {
        if (node.isArray()) {
            for (int i = 0; i < node.size(); i++) {
                if (node.get(i).isNull()) {
                    ((ArrayNode) node).set(i, new DoubleNode(defaultValue));
                }
            }
        }
        return node;
    }

    private JsonNode replaceNullWithDefault(JsonNode node, int defaultValue) {
        if (node.isArray()) {
            for (int i = 0; i < node.size(); i++) {
                if (node.get(i).isNull()) {
                    ((ArrayNode) node).set(i, new IntNode(defaultValue));
                }
            }
        }
        return node;
    }


    public SearchStockInfoResult fetchStockInfo(SearchStockInfoRequest request) throws StockInfoNotFoundException {
        String url = "https://query1.finance.yahoo.com/v8/finance/chart/" + request.getSymbol() + "?interval=1d&range=30d";

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

            JsonNode volumeNode = replaceNullWithDefault(quoteNode.path("volume"), 0);
            JsonNode lowNode = replaceNullWithDefault(quoteNode.path("low"), 0.00);
            JsonNode closeNode = replaceNullWithDefault(quoteNode.path("close"), 0.00);
            JsonNode openNode = replaceNullWithDefault(quoteNode.path("open"), 0.00);
            JsonNode highNode = replaceNullWithDefault(quoteNode.path("high"), 0.00);

            Integer[] volumeArray = objectMapper.treeToValue(volumeNode, Integer[].class);
            double[] lowArray = objectMapper.treeToValue(lowNode, double[].class);
            double[] closeArray = objectMapper.treeToValue(closeNode, double[].class);
            double[] openArray = objectMapper.treeToValue(openNode, double[].class);
            double[] highArray = objectMapper.treeToValue(highNode, double[].class);
            long[] timestampArray = objectMapper.treeToValue(timestampNode, long[].class);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DecimalFormat decimalFormat = new DecimalFormat("#.00");

            List<StockInfo> stockInfoList = new ArrayList<>();

            for (int i = 0; i < timestampArray.length; i++) {
                Instant instant = Instant.ofEpochSecond(timestampArray[i]);
                LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
                String formattedDate = dateTime.format(formatter);

                StockInfo stockInfo = StockInfo.builder()
                        .withTimestamp(formattedDate)
                        .withOpen(Double.parseDouble(decimalFormat.format(openArray[i])))
                        .withClose(Double.parseDouble(decimalFormat.format(closeArray[i])))
                        .withLow(Double.parseDouble(decimalFormat.format(lowArray[i])))
                        .withHigh(Double.parseDouble(decimalFormat.format(highArray[i])))
                        .withVolume(volumeArray[i])
                        .build();

                stockInfoList.add(stockInfo);
            }

            return SearchStockInfoResult.builder()
                    .withStockInfoList(stockInfoList)
                    .build();

        } catch (IOException e) {
            logger.error("Error fetching stock information: ", e);
            throw new StockInfoNotFoundException("Stock is not found", e);
        }
    }
}