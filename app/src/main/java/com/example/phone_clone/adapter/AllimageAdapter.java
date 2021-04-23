package com.example.phone_clone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.phone_clone.R;

import java.util.ArrayList;
import java.util.List;

import Model.Image;

public class AllimageAdapter extends RecyclerView.Adapter<AllimageAdapter.myviewholder> {
List<Image> list;
Context context;
    public AllimageAdapter(Context context, List<Image> list) {
this.context=context;
this.list=list;

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.single_image_dailoge,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

        try {
            Glide.with(context).load(list.get(position).getPath())
                    .placeholder(R.drawable.ic_launcher_foreground).centerCrop()
                    .into(holder.img);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {

        ImageView img;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.galleryimage);

        }

    }
}
