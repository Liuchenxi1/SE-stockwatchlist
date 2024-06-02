package com.nashss.se.stockwatchlist.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.stockwatchlist.activity.requests.DeleteWatchListRequest;
import com.nashss.se.stockwatchlist.activity.results.DeleteWatchListResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteWatchListLambda
        extends LambdaActivityRunner<DeleteWatchListRequest, DeleteWatchListResult>
        implements RequestHandler<AuthenticatedLambdaRequest<DeleteWatchListRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<DeleteWatchListRequest> input, Context context) {
        return super.runActivity(() -> {
                    DeleteWatchListRequest unauthenticatedRequest = input.fromBody(DeleteWatchListRequest.class);

                    log.info("Deleting watchlist: {}", unauthenticatedRequest.getWatchlistName());

                    return input.fromUserClaims(claims ->
                            DeleteWatchListRequest.builder()
                                    .withEmail(claims.get("email"))
                                    .withWatchlistName(unauthenticatedRequest.getWatchlistName())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideDeleteWatchListActivity().handleRequest(request)
        );
    }
}
