package com.example.phone_clone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phone_clone.R;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder>{
    ArrayList<String> VideosList;
    Context mContext;


    public VideoAdapter(ArrayList<String> videosList, Context context) {
        VideosList = videosList;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (parent.getContext ()).
                inflate ( R.layout.videos_layout,parent,false);
        VideoAdapter.ViewHolder viewHolder = new VideoAdapter.ViewHolder ( view );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.videoTile.setText ( VideosList.get ( position ) );

    }

    @Override
    public int getItemCount() {
        return VideosList.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView videoImage;
        TextView videoTile,videoFileSize;
        CheckBox videoCheckbox;

        public ViewHolder(@NonNull View itemView) {
            super ( itemView );
            videoImage = itemView.findViewById ( R.id.video_thumb );
            videoTile = itemView.findViewById ( R.id.video_title );
            videoFileSize = itemView.findViewById ( R.id.videos_size );
            videoCheckbox = itemView.findViewById ( R.id.videos_checkbox);


        }
    }
}

