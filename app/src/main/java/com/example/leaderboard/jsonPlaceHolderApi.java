package com.example.leaderboard;

import com.example.leaderboard.models.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface jsonPlaceHolderApi {

    @GET("api/leaderbord")
    Call<ApiResponse> getUsers(@Query("currentUserId") String currentUserId);
}
