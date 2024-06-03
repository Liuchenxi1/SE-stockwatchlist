package com.nashss.se.stockwatchlist.activity.requests;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GetWatchListRequest.Builder.class)
public class GetWatchListRequest {
    private final String email;
    private final String watchlistName;

    public GetWatchListRequest(String email, String watchlistName) {
        this.email = email;
        this.watchlistName = watchlistName;
    }

    public String getEmail() {
        return email;
    }

    public String getWatchlistName() {
        return watchlistName;
    }

    @Override
    public String toString() {
        return "GetWatchListRequest{" +
                "email='" + email + '\'' +
                ", watchlistName='" + watchlistName + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder{

        private String email;
        private String watchlistName;

        public Builder withEmail(String email){
            this.email = email;
            return this;
        }

        public Builder withWatchlistName(String watchlistName) {
            this.watchlistName = watchlistName;
            return this;
        }

        public GetWatchListRequest build() {return new GetWatchListRequest(email,watchlistName); }

    }

}
