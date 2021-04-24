package com.example.phone_clone.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phone_clone.R;

import java.util.List;

import com.example.phone_clone.Model.FileToSendPath;

public class FileReciveAdapter extends RecyclerView.Adapter<FileReciveAdapter.myviewholder> {
    List<FileToSendPath> mPathsList;
    public FileReciveAdapter(List<FileToSendPath> list) {

        this.mPathsList=list;
    }
    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.recive_file_layout,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final myviewholder holder, int position) {
        holder.txt.setText(mPathsList.get(position).getName());
        holder.txt2.setText("15MB");
    }

    @Override
    public int getItemCount() {
        return mPathsList.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder
    {

        ImageView img,img2;
        TextView txt,txt2;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            txt=(TextView)itemView.findViewById(R.id.txtname);
            txt2=(TextView)itemView.findViewById(R.id.txtstroage);
        }

    }
}

