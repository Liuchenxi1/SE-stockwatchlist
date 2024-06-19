package com.nashss.se.stockwatchlist.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.stockwatchlist.activity.requests.AddStockIntoWatchListRequest;
import com.nashss.se.stockwatchlist.activity.results.AddStockIntoWatchListResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddStockIntoWatchListLambda
        extends LambdaActivityRunner<AddStockIntoWatchListRequest, AddStockIntoWatchListResult>
        implements RequestHandler<AuthenticatedLambdaRequest<AddStockIntoWatchListRequest>, LambdaResponse> {

       private final Logger log = LogManager.getLogger();
       @Override
       public LambdaResponse handleRequest (AuthenticatedLambdaRequest<AddStockIntoWatchListRequest> input, Context context) {
           return super.runActivity(() -> {
               AddStockIntoWatchListRequest authenticatedRequest = input.fromBody(AddStockIntoWatchListRequest.class);
               log.info("the stock symbol is {}", authenticatedRequest.getStockSymbol());
               return input.fromUserClaims(claims ->
                       AddStockIntoWatchListRequest.builder()
                               .withEmail(claims.get("email"))
                               .withWatchlistName(authenticatedRequest.getWatchlistName())
                               .withStockSymbol(authenticatedRequest.getStockSymbol())
                               .build());
           }, (request, serviceComponent) ->
                   serviceComponent.provideAddStockIntoWatchListActivity().handleRequest(request));
       }
}
