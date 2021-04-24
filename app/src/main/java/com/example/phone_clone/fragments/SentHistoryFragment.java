package com.example.phone_clone.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phone_clone.R;
import com.example.phone_clone.adapter.ReceivesHistoryAdapter;
import com.example.phone_clone.adapter.SentHistoryAdapter;

import java.util.ArrayList;

public class SentHistoryFragment extends Fragment {
    RecyclerView mRecyclerView;
    ArrayList<String> sentList;
  SentHistoryAdapter mSentHistoryAdapter;

    public SentHistoryFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate ( R.layout.sent_history_fragment ,container,false);
        mRecyclerView = view.findViewById ( R.id.recycler );
        mRecyclerView.setLayoutManager ( new LinearLayoutManager (getContext ()) );
        sentList= new ArrayList<> ();
        sentList.add ( "Diamond Heart" );
        sentList.add ( "Lily" );
        sentList.add ( "Sing me to sleep" );
        sentList.add ( "Diamond Heart" );
        sentList.add ( "Alone" );
        sentList.add ( "Darkside" );
        sentList.add ( "Faded" );
        sentList.add ( "Faded" );
        sentList.add ( "Darkside" );
        mSentHistoryAdapter = new SentHistoryAdapter (sentList ,getContext ());
        mRecyclerView.setAdapter (mSentHistoryAdapter );

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
    }


}
