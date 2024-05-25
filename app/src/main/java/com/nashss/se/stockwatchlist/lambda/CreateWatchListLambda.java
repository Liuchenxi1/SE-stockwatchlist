package com.nashss.se.stockwatchlist.lambda;

import com.nashss.se.stockwatchlist.activity.requests.CreateWatchListRequest;
import com.nashss.se.stockwatchlist.activity.results.CreateWatchListResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import javax.management.InvalidAttributeValueException;

public class CreateWatchListLambda
        extends LambdaActivityRunner<CreateWatchListRequest, CreateWatchListResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateWatchListRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest (AuthenticatedLambdaRequest<CreateWatchListRequest> input, Context context) {
        return super.runActivity(() -> {
            CreateWatchListRequest unauthenticatedRequest = input.fromBody(CreateWatchListRequest.class);
            return input.fromUserClaims(claims ->
                    CreateWatchListRequest.builder()
                            .withEmail(claims.get("email"))
                            .withWatchListName(unauthenticatedRequest.getWatchListName())
                            .withStockSymbols(unauthenticatedRequest.getStockSymbols())
                            .build());
        },
                (request, serviceComponent) ->
                        serviceComponent.provideCreateWatchListActivity().handleRequest(request))
                ;
    }
}
