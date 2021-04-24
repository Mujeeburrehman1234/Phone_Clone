package com.example.phone_clone.adapter;

import android.app.Activity;
import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.phone_clone.R;

import java.util.List;

import Model.AppIication;

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.myviewholder> {
    List<AppIication> list;
    Activity activity;
    public ApplicationAdapter(Activity activity, List<AppIication> list)
    {
     this.list=list;
     this.activity=activity;
    }
    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.single_application,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.appIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);


        Glide.with(activity).load(list.get(position).getIcon())
                .placeholder(R.drawable.ic_launcher_foreground).centerCrop()
                .into(holder.appIcon);
        holder.appName.setText(list.get(position).getAppname());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {

        ImageView appIcon;
        TextView appName;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            appIcon = (ImageView) itemView.findViewById(R.id.imgapp_icon);
            appName=(TextView) itemView.findViewById(R.id.txtapp_name);

        }

    }
}
