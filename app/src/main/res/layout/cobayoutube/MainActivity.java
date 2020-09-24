package com.example.cobayoutube;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvVideo;
    private JSONObject obj;
    private ArrayList<Video> daftarVideo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvVideo= findViewById(R.id.rv_video);
        rvVideo.setHasFixedSize(true);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com")
                .addConverterFactory(ScalarsConverterFactory.create())
                // add other factories here, if needed.
                .build();

        YoutubeService service = retrofit.create(YoutubeService.class);
        Call<String> stringCall = service.listVideos();
        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String responseString = response.body();

                    try{
                        obj = new JSONObject(responseString);
                        JSONArray items = obj.getJSONArray("items");

                        for (int i = 0; i < items.length(); i++) {
                            JSONObject video = new JSONObject(items.getString(i));
                            JSONObject detailVideo = new JSONObject(video.getString("snippet"));
                            JSONObject thumbnailsVideo = new JSONObject(detailVideo.getString("thumbnails"));
                            JSONObject thumbhgVideo = new JSONObject(thumbnailsVideo.getString("high"));
                            JSONObject videoid = new JSONObject(video.getString("id"));

                            Video vid = new Video();
                            vid.setJudulVideo(detailVideo.getString("title"));
                            vid.setChannelVideo(detailVideo.getString("channelId"));
                            vid.setDeskripsiVideo(detailVideo.getString("description"));
                            vid.setThumbnailVideo(thumbhgVideo.getString("url"));
                            vid.setVideoID(videoid.getString("videoId"));

                            daftarVideo.add(vid);
                        }

                        showRecyclerList();

                    } catch (Throwable t){
                        Log.d("hasilRequest",  "error gan");
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("hasilRequest",  "error");
            }
        });

    }

    private void showSelectedVideo(Video video) {
        Intent moveWithObjectIntent = new Intent(MainActivity.this, DetailVideo.class);
        moveWithObjectIntent.putExtra(DetailVideo.EXTRA_VIDEO, video);
        startActivity(moveWithObjectIntent);
    }

    private void showRecyclerList(){
        rvVideo.setLayoutManager(new LinearLayoutManager(this));
        ListVideoAdapter listVideoAdapter = new ListVideoAdapter(daftarVideo);
        rvVideo.setAdapter(listVideoAdapter);
        listVideoAdapter.setOnItemClickCallback(new ListVideoAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Video data) {
                showSelectedVideo(data);
            }
    });
    }
}
