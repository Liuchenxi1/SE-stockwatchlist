package com.nashss.se.stockwatchlist.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.nashss.se.stockwatchlist.dynamodb.models.WatchList;
import com.nashss.se.stockwatchlist.execptions.WatchlistIsNotFoundException;
import com.nashss.se.stockwatchlist.metrics.MetricsPublisher;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WatchListDao {

    private final DynamoDBMapper dynamoDBMapper;
    private final MetricsPublisher metricsPublisher;

    @Inject

    public WatchListDao(DynamoDBMapper dynamoDBMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.metricsPublisher = metricsPublisher;
    }

    public List<WatchList> getWatchListsByEmail(String email) {

        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":v1", new AttributeValue().withS(email));

        DynamoDBQueryExpression<WatchList> queryExpression = new DynamoDBQueryExpression<WatchList>()
                .withKeyConditionExpression("UserEmail = :v1")
                .withExpressionAttributeValues(valueMap);

        List<WatchList> queryList = dynamoDBMapper.query(WatchList.class, queryExpression);

        if (queryList == null || queryList.isEmpty()) {
            queryList = new ArrayList<>();
        }

        return queryList;
    }

    public WatchList saveWatchList(WatchList watchList) {
        this.dynamoDBMapper.save(watchList);
        return watchList;
    }

    public WatchList getWatchlist(WatchList watchList) {
        return this.dynamoDBMapper.load(watchList);
    }

    public WatchList deleteWatchList(WatchList watchList) {
        this.dynamoDBMapper.delete(watchList);
        return watchList;
    }

}
