package com.example.cobayoutube;

import android.os.Parcel;
import android.os.Parcelable;

public class Video implements Parcelable {
    private String judulVideo;
    private String deskripsiVideo;
    private String channelVideo;
    private String thumbnailVideo;
    private String videoID;

    public String getJudulVideo() {
        return judulVideo;
    }

    public void setJudulVideo(String judulVideo) {
        this.judulVideo = judulVideo;
    }

    public String getDeskripsiVideo() {
        return deskripsiVideo;
    }

    public void setDeskripsiVideo(String deskripsiVideo) {
        this.deskripsiVideo = deskripsiVideo;
    }

    public String getChannelVideo() {
        return channelVideo;
    }

    public void setChannelVideo(String channelVideo) {
        this.channelVideo = channelVideo;
    }

    public String getThumbnailVideo() {
        return thumbnailVideo;
    }

    public void setThumbnailVideo(String thumbnailVideo) {
        this.thumbnailVideo = thumbnailVideo;
    }

    public String getVideoID(){
        return videoID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    protected Video(Parcel in) {

        judulVideo = in.readString();
        deskripsiVideo = in.readString();
        channelVideo = in.readString();
        thumbnailVideo = in.readString();
        videoID = in.readString();
    }

    public Video() { }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(judulVideo);
        dest.writeString(deskripsiVideo);
        dest.writeString(channelVideo);
        dest.writeString(thumbnailVideo);
        dest.writeString(videoID);
    }
    @Override
    public int describeContents() {
        return 0;
    }
    public static final Creator<Video> CREATOR = new Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel in) {
            return new Video(in);
        }
        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };

}
