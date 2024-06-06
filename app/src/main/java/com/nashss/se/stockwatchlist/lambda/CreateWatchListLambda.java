package com.nashss.se.stockwatchlist.lambda;

import com.nashss.se.stockwatchlist.activity.requests.CreateWatchListRequest;
import com.nashss.se.stockwatchlist.activity.results.CreateWatchListResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CreateWatchListLambda
        extends LambdaActivityRunner<CreateWatchListRequest, CreateWatchListResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateWatchListRequest>, LambdaResponse> {
    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest (AuthenticatedLambdaRequest<CreateWatchListRequest> input, Context context) {
        return super.runActivity(() -> {
            CreateWatchListRequest unauthenticatedRequest = input.fromBody(CreateWatchListRequest.class);
            log.info("the stock symbols are{}" ,unauthenticatedRequest.getStockSymbols());
            return input.fromUserClaims(claims ->
                    CreateWatchListRequest.builder()
                            .withEmail(claims.get("email"))
                            .withWatchlistName(unauthenticatedRequest.getWatchlistName())
                            .withStockSymbols(unauthenticatedRequest.getStockSymbols())
                            .build());
        },
                (request, serviceComponent) ->
                        serviceComponent.provideCreateWatchListActivity().handleRequest(request));
    }

}
