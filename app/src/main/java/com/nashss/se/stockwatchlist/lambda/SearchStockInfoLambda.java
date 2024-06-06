package com.nashss.se.stockwatchlist.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.nashss.se.stockwatchlist.activity.requests.DeleteWatchListRequest;
import com.nashss.se.stockwatchlist.activity.requests.SearchStockInfoRequest;
import com.nashss.se.stockwatchlist.activity.results.SearchStockInfoResult;



public class SearchStockInfoLambda
        extends LambdaActivityRunner<SearchStockInfoRequest,SearchStockInfoResult>
        implements RequestHandler<AuthenticatedLambdaRequest<SearchStockInfoRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest (AuthenticatedLambdaRequest<SearchStockInfoRequest> input, Context context) {
       return super.runActivity(() -> {
           SearchStockInfoRequest unauthenticatedRequest = input.fromBody(SearchStockInfoRequest.class);

           return input.fromUserClaims(claims ->
                   SearchStockInfoRequest.builder()
                           .withSymbol(unauthenticatedRequest.getSymbol())
                           .withTimestamps(unauthenticatedRequest.getTimestamps())
                           .withOpens(unauthenticatedRequest.getOpens())
                           .withCloses(unauthenticatedRequest.getCloses())
                           .withLows(unauthenticatedRequest.getLows())
                           .withHighs(unauthenticatedRequest.getHighs())
                           .withVolumes(unauthenticatedRequest.getVolumes())
                           .build());
       },
               (request, serviceComponent) ->
                       serviceComponent.provideSearchStockInfoActivity().fetchStockInfo(request));
    }
}

