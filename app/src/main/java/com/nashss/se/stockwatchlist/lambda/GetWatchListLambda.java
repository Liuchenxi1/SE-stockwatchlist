package com.nashss.se.stockwatchlist.lambda;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.stockwatchlist.activity.requests.GetWatchListRequest;
import com.nashss.se.stockwatchlist.activity.results.GetWatchListResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetWatchListLambda extends LambdaActivityRunner<GetWatchListRequest, GetWatchListResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetWatchListRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetWatchListRequest> input, Context context) {
        return super.runActivity(() -> {
                    GetWatchListRequest authenticatedRequest = input.fromBody(GetWatchListRequest.class);

                    log.info("getting watchlist: {}", authenticatedRequest.getWatchlistName());

                    return input.fromUserClaims(claims ->
                            GetWatchListRequest.builder()
                                    .withEmail(claims.get("email"))
                                    .withWatchlistName(authenticatedRequest.getWatchlistName())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideGetWatchListActivity().handleRequest(request)
        );
    }
}
