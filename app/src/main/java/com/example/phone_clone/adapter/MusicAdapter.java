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

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder>{
    ArrayList<String> musicList;
    Context mContext;

    public MusicAdapter(ArrayList<String> musicList, Context context) {
        this.musicList = musicList;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (parent.getContext ()).
                inflate ( R.layout.music_layout,parent,false);
        ViewHolder viewholder = new ViewHolder ( view );
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.musicTile.setText (musicList.get ( position ));

    }

    @Override
    public int getItemCount() {
        return musicList.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView musicImage;
        TextView musicTile,musicFileSize;
        CheckBox musicCheckbox;
        public ViewHolder(@NonNull View itemView) {
            super ( itemView );
          musicImage = itemView.findViewById ( R.id.music_thumb );
          musicTile = itemView.findViewById ( R.id.music_title );
          musicFileSize = itemView.findViewById ( R.id.music_size );

        }
    }
}
