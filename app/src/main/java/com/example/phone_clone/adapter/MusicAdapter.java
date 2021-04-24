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
import java.util.List;

import Model.Track;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder>{
    List<Track> musicList;
    Context mContext;

    public MusicAdapter(List<Track> musicList, Context context) {
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

        final int pos=position;
        final String text = musicList.get(position).getTitle();
        final String size = musicList.get(position).getSize();
        final String length = musicList.get(position).getLength();
        holder.title.setText(text);
        holder.size.setText(size);
        holder.length.setText(length);
    }

    @Override
    public int getItemCount() {
        return musicList.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

       public CheckBox musicCheckbox;
        public TextView title;
        public  TextView size;
        public TextView length;
        public ImageView musicIcon;
        public ViewHolder(@NonNull View itemView) {
            super ( itemView );
          musicIcon = itemView.findViewById ( R.id.music_thumb );
          title = itemView.findViewById ( R.id.music_title );
          size = itemView.findViewById ( R.id.music_size );
          length=itemView.findViewById(R.id.music_lenght);

        }
    }
}
