package com.nashss.se.stockwatchlist.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GetWatchListsRequest.Builder.class)
public class GetWatchListsRequest {

    private final String email;

    private GetWatchListsRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "GetWatchListsRequest{" +
                "email='" + email + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder{
        private String email;

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public GetWatchListsRequest build() {
            return new GetWatchListsRequest(email);
        }
    }
}
