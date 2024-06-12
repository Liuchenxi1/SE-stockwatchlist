package com.nashss.se.stockwatchlist.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.nashss.se.stockwatchlist.activity.requests.SearchStockInfoRequest;
import com.nashss.se.stockwatchlist.activity.results.SearchStockInfoResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class SearchStockInfoLambda
        extends LambdaActivityRunner<SearchStockInfoRequest,SearchStockInfoResult>
        implements RequestHandler<AuthenticatedLambdaRequest<SearchStockInfoRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest (AuthenticatedLambdaRequest<SearchStockInfoRequest> input, Context context) {
        log.info("handleRequest");

        return super.runActivity(
                () -> input.fromPath(path ->
                        SearchStockInfoRequest.builder()
                                .withSymbol(path.get("symbol"))
                                .build()),
               (request, serviceComponent) ->
                       serviceComponent.provideSearchStockInfoActivity().fetchStockInfo(request)
        );
    }
}

