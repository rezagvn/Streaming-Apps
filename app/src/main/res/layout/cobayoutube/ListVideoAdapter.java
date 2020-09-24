package com.example.cobayoutube;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListVideoAdapter extends RecyclerView.Adapter<ListVideoAdapter.ListViewHolder> {
    private ArrayList<Video> listVideo;

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public ListVideoAdapter(ArrayList<Video> list) {
        this.listVideo= list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_video, viewGroup, false);
        return new ListViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Video video = listVideo.get(position);
        Glide.with(holder.itemView.getContext())
                .load(video.getThumbnailVideo())
                .apply(new RequestOptions().override(480, 360))
                .into(holder.tvThumbnail);
        holder.tvName.setText(video.getJudulVideo());
        holder.tvDetail.setText(video.getDeskripsiVideo());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listVideo.get(holder.getAdapterPosition()));
            }
        });

        }



    @Override
    public int getItemCount() {
        return listVideo.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvDetail;
        ImageView tvThumbnail;

        ListViewHolder(View itemView ) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_item_name);
            tvDetail = itemView.findViewById(R.id.tv_item_detail);
            tvThumbnail = itemView.findViewById(R.id.img_thumbnail);


        }
    }


    public interface OnItemClickCallback {
        void onItemClicked(Video data);
    }
}
