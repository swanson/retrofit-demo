package com.swanson.octodroid;

import retrofit.RestAdapter;

public class GitHubService {
    private static String API_URL = "https://api.github.com";

    public static GitHub getService() {
        return new RestAdapter.Builder()
                    .setServer(API_URL)
                    .build()
                    .create(GitHub.class);
    }
}
