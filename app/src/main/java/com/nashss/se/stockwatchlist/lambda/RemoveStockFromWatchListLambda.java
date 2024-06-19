package com.nashss.se.stockwatchlist.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.stockwatchlist.activity.requests.AddStockIntoWatchListRequest;
import com.nashss.se.stockwatchlist.activity.requests.RemoveStockFromWatchListRequest;
import com.nashss.se.stockwatchlist.activity.results.RemoveStockFromWatchListResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RemoveStockFromWatchListLambda
        extends LambdaActivityRunner<RemoveStockFromWatchListRequest, RemoveStockFromWatchListResult>
        implements RequestHandler<AuthenticatedLambdaRequest<RemoveStockFromWatchListRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest (AuthenticatedLambdaRequest<RemoveStockFromWatchListRequest> input, Context context) {
        return super.runActivity(() -> {
            RemoveStockFromWatchListRequest authenticatedRequest = input.fromBody(RemoveStockFromWatchListRequest.class);
            log.info("the stock symbol is {}", authenticatedRequest.getStockSymbol());
            return input.fromUserClaims(claims ->
                    RemoveStockFromWatchListRequest.builder()
                            .withEmail(claims.get("email"))
                            .withWatchlistName(authenticatedRequest.getWatchlistName())
                            .withStockSymbol(authenticatedRequest.getStockSymbol())
                            .build());
        }, (request, serviceComponent) ->
                serviceComponent.provideRemoveStockFromWatchListActivity().handleRequest(request));
    }
}
