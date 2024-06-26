package com.nashss.se.stockwatchlist.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.stockwatchlist.activity.requests.GetWatchListsRequest;
import com.nashss.se.stockwatchlist.activity.results.GetWatchListsResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetWatchListsLambda extends LambdaActivityRunner<GetWatchListsRequest, GetWatchListsResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetWatchListsRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetWatchListsRequest> input, Context context) {
        return super.runActivity(() -> {
            return input.fromUserClaims(claims ->
                    GetWatchListsRequest.builder()
                            .withEmail(claims.get("email"))
                            .build());
        },
                (request, serviceComponent) ->
                        serviceComponent.provideGetWatchListsActivity().handleRequest(request)
                );
    }
}
