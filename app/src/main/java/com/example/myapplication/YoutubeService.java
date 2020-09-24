package com.example.myapplication;


import retrofit2.Call;
import retrofit2.http.GET;

public interface YoutubeService {
    @GET("/youtube/v3/search?part=snippet&maxResults=25&type=video&q=Larva&key=AIzaSyDbRR_9ZWv-AyOvQWbgUwCcaA-Yoni-7wQ")
    Call<String> listVideos();
}
