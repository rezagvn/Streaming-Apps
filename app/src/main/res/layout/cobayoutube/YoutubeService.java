package com.example.cobayoutube;


import retrofit2.Call;
import retrofit2.http.GET;

public interface YoutubeService {
    @GET("/youtube/v3/search?part=snippet&maxResults=25&type=video&q=[YOUR_KEYWORD]&key=[YOUR_API]")
    Call<String> listVideos();
}
