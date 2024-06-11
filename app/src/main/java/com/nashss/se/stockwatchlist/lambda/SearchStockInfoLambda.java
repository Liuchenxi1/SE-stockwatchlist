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
           //it should be fromPath,
           SearchStockInfoRequest unauthenticatedRequest = input.fromBody(SearchStockInfoRequest.class);

           return input.fromUserClaims(claims ->
                   SearchStockInfoRequest.builder()
                           .withSymbol(unauthenticatedRequest.getSymbol())
                           .withStockInfoList(unauthenticatedRequest.getStockInfoList())
                           .build());
       },
               (request, serviceComponent) ->
                       serviceComponent.provideSearchStockInfoActivity().fetchStockInfo(request));
    }
}

