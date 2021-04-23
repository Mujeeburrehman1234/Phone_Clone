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

public class ReceivesHistoryAdapter extends RecyclerView.Adapter<ReceivesHistoryAdapter.ViewHolder> {
    ArrayList<String> ReceivesList;
    Context mContext;

    public ReceivesHistoryAdapter(ArrayList<String> receivesList, Context context) {
        ReceivesList = receivesList;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (parent.getContext ()).
                inflate ( R.layout.receives_history_layout,parent,false);
       ViewHolder viewHolder =new ViewHolder ( view ) ;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.receivesTitle.setText (ReceivesList.get ( position ));

    }

    @Override
    public int getItemCount() {
        return  ReceivesList.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView receivesImage;
        TextView receivesTitle,receivesFileSize;
        CheckBox receivesCheckbox;
 public ViewHolder(@NonNull View itemView) {
            super ( itemView );
            receivesImage = itemView.findViewById ( R.id.receive_history_thumb );
            receivesTitle = itemView.findViewById ( R.id.receive_history_title );
            receivesFileSize = itemView.findViewById ( R.id.receive_history_size );
        }
    }
}
