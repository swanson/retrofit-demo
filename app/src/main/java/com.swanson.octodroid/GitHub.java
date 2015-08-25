package com.swanson.octodroid;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface GitHub {

    @GET("/users/{user}/repos") void repositories(
            @Path("user") String username,
            Callback<List<Repository>> callback
    );
}
