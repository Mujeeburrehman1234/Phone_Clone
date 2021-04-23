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

public class FilesAdapter extends RecyclerView.Adapter<FilesAdapter.ViewHolder>{
    public FilesAdapter(ArrayList<String> filesList, Context context) {
        this.filesList = filesList;
        mContext = context;
    }

    ArrayList<String> filesList;
    Context mContext;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (parent.getContext ()).
                inflate ( R.layout.files_layout,parent,false);
       FilesAdapter.ViewHolder viewHolder = new FilesAdapter.ViewHolder ( view );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.fileTile.setText ( filesList.get ( position ) );

    }

    @Override
    public int getItemCount() {
        return filesList.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fileImage;
        TextView fileTile,fileDate;
        CheckBox fileCheckbox;
    public ViewHolder(@NonNull View itemView) {
        super ( itemView );
        fileImage = itemView.findViewById ( R.id.files_thumb );
        fileTile = itemView.findViewById ( R.id.files_title );
        fileDate = itemView.findViewById ( R.id.videos_size );
        fileCheckbox = itemView.findViewById ( R.id.files_checkbox);


    }
}
}
