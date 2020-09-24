package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView rvVideo;
    private JSONObject obj;
    private ArrayList<Video> daftarVideo = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home,container, false);

        rvVideo=root.findViewById(R.id.rv_video);
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
        return root;
    }



    private void showSelectedVideo(Video video) {
        Intent moveWithObjectIntent = new Intent(getContext(), DetailVideo.class);
        moveWithObjectIntent.putExtra(DetailVideo.EXTRA_VIDEO, video);
        startActivity(moveWithObjectIntent);
    }

    private void showRecyclerList(){
        rvVideo.setLayoutManager(new LinearLayoutManager(getContext()));
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

