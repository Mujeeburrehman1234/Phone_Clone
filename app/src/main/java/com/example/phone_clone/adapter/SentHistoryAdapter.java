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

public class SentHistoryAdapter extends RecyclerView.Adapter<SentHistoryAdapter.ViewHolder> {
    ArrayList<String> SentList;
    Context mContext;

    public SentHistoryAdapter(ArrayList<String> sentList, Context context) {
        SentList = sentList;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (parent.getContext ()).
                inflate ( R.layout.sent_history_layout,parent,false);
       ViewHolder viewHolder =new ViewHolder ( view ) ;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.sentTitle.setText ( SentList.get ( position ));

    }

    @Override
    public int getItemCount() {
        return   SentList.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView sentImage;
        TextView sentTitle,sentFileSize;
        CheckBox sentCheckbox;
 public ViewHolder(@NonNull View itemView) {
            super ( itemView );
            sentImage = itemView.findViewById ( R.id.sent_history_thumb );
            sentTitle = itemView.findViewById ( R.id.sent_history_title );
            sentFileSize = itemView.findViewById ( R.id.sent_history_size );
        }
    }
}
