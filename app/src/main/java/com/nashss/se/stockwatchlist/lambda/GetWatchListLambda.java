package com.nashss.se.stockwatchlist.lambda;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.stockwatchlist.activity.requests.GetWatchListRequest;
import com.nashss.se.stockwatchlist.activity.results.GetWatchListResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetWatchListLambda extends LambdaActivityRunner<GetWatchListRequest, GetWatchListResult>
        implements RequestHandler<LambdaRequest<GetWatchListRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetWatchListRequest> input, Context context) {
        log.info("handleRequest");

        return super.runActivity(() -> input.fromPath(path ->
                GetWatchListRequest.builder()
                        .withWatchlistName(path.get("watchlistName"))
                        .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetWatchListActivity().handleRequest(request)
        );

    }
}
