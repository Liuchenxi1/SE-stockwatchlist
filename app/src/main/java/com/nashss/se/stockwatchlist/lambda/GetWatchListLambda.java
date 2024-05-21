package com.nashss.se.stockwatchlist.lambda;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetWatchListLambda implements RequestHandler<LambdaRequest<String>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<String> input, Context context) {
        return null;
    }
}
